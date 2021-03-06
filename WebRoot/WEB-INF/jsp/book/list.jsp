<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/ga.js"></script>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-table.css">
<link rel="stylesheet" href="../css/bootstrap-editable.css">
<link rel="stylesheet" href="../css/index.css">
<title>Book list</title>
</head>
<body>
<div class="container" style="height:300px">
    <h1>Bootstrap Table Examples 分页 列可选择 列可排序 单页数据导出<a href="${basePath}login/logout" class="btn btn-primary" role="button" >logout</a></h1>
    <div id="toolbar">
        <button id="remove" class="btn btn-danger" disabled>
            <i class="glyphicon glyphicon-remove"></i> Delete
        </button>
        <button id="ajaxTest" class="btn btn-danger">
            <i class="glyphicon glyphicon-ok"></i> ajaxTest
        </button>
    </div>
    <table id="table" data-toolbar="#toolbar" data-search="true" data-show-refresh="true"
           data-show-toggle="true" data-show-columns="true" data-show-export="true" data-detail-view="true"
           data-detail-formatter="detailFormatter" data-minimum-count-columns="2" data-show-pagination-switch="true"
           data-pagination="true" data-id-field="id" data-page-list="[10, 25, 50, 100, ALL]"
           data-show-footer="false" data-side-pagination="server"
           data-response-handler="responseHandler">
    </table>
</div>
<script>
    var $table = $('#table'),$remove = $('#remove'),selections = [];
    var data_url = "/myssm/book/getBooKList"
    function initTable() {
        $table.bootstrapTable({
        	url:data_url,
        	height: getHeight(),
            columns: [
            [
                {
                    field: 'state',checkbox: true,rowspan: 2,align: 'center',valign: 'middle'
                }, {
                    title: '图书编号',width:'120px',field: 'bookId',rowspan: 2,align: 'center',valign: 'middle',
                    sortable: true,footerFormatter: totalTextFormatter
                }, {
                    title: '详细信息',colspan: 3,align: 'center'
                }
            ],
            [
                {
                    field: 'name',title: '书名',width:'300px',sortable: true,editable: true,
                    footerFormatter: totalNameFormatter,align: 'center'
                }, {
                    field: 'price',title: '价格',sortable: false,align: 'center',
                    editable: {
                        type: 'text',
                        title: '价格',
                        validate: function (value) {
                            value = $.trim(value);
                            if (!value) {
                                return 'This field is required';
                            }
                            if (!/^\$/.test(value)) {
                                //return 'This field needs to start width $.'
                            }
                            var data = $table.bootstrapTable('getData'),
                                index = $(this).parents('tr').data('index');
                            console.log("---->data : id = " + data[index].bookId + " sort = price" + " newvalue = " +value);
                            ajaxEdit(data[index].bookId,value);
                            return '';
                        }
                    },
                    footerFormatter: totalPriceFormatter
                }, {
                    field: 'operate',title: '操作',align: 'center',
                    events: operateEvents,formatter: operateFormatter
                }
            ]
        ]
        });
        // sometimes footer render error.
        setTimeout(function () {
            $table.bootstrapTable('resetView');
        }, 200);
        $table.on('check.bs.table uncheck.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);

            // save your data, here just save the current page
            selections = getIdSelections();
            // push or splice the selections if you want to save all data selections
        });
        $table.on('expand-row.bs.table', function (e, index, row, $detail) {
            if (index % 2 == 1) {
                $detail.html('Loading from ajax request...');
                $.get('LICENSE', function (res) {
                    $detail.html(res.replace(/\n/g, '<br>'));
                });
            }
        });
        $table.on('all.bs.table', function (e, name, args) {
            console.log(name, args);
        });
        $remove.click(function () {
            var ids = getIdSelections();
            $table.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            $remove.prop('disabled', true);
        });
        $(window).resize(function () {
            $table.bootstrapTable('resetView', {
                height: getHeight()
            });
        });
    }

    function getIdSelections() {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }

    function responseHandler(res) {
        $.each(res.rows, function (i, row) {
            row.state = $.inArray(row.id, selections) !== -1;
        });
        return res;
    }

    function detailFormatter(index, row) {
        var html = [];
        $.each(row, function (key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return html.join('');
    }

    function operateFormatter(value, row, index) {
        return [
            '<a class="like" href="javascript:void(0)" title="Like">',
            '<i class="glyphicon glyphicon-heart"></i>',
            '</a>  ',
            '<a class="remove" href="javascript:void(0)" title="Remove">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join('');
    }

    window.operateEvents = {
        'click .like': function (e, value, row, index) {
            alert('You click like action, row: ' + JSON.stringify(row));
        },
        'click .remove': function (e, value, row, index) {
            $table.bootstrapTable('remove', {
                field: 'id',
                values: [row.id]
            });
        }
    };

    function totalTextFormatter(data) {
        return 'Total';
    }

    function totalNameFormatter(data) {
        return data.length;
    }

    function totalPriceFormatter(data) {
        var total = 0;
        $.each(data, function (i, row) {
            total += +(row.price.substring(1));
        });
        return '$' + total;
    }

    function getHeight() {
        return $(window).height() - $('h1').outerHeight(true);
    }

    $(function () {
        var scripts = [
                location.search.substring(1) || '../js/bootstrap-table.js',
                '../js/bootstrap-table-export.js',
                '../js/tableExport.js',
                '../js/bootstrap-table-editable.js',
                '../js/bootstrap-editable.js'
            ],
            eachSeries = function (arr, iterator, callback) {
                callback = callback || function () {};
                if (!arr.length) {
                    return callback();
                }
                var completed = 0;
                var iterate = function () {
                    iterator(arr[completed], function (err) {
                        if (err) {
                            callback(err);
                            callback = function () {};
                        }
                        else {
                            completed += 1;
                            if (completed >= arr.length) {
                                callback(null);
                            }
                            else {
                                iterate();
                            }
                        }
                    });
                };
                iterate();
            };

        eachSeries(scripts, getScript, initTable);
        
        $("#ajaxTest").click(function () {
        	$table.bootstrapTable('refresh', {url: '/myssm/book/getBooKList?Search=111'});
        });
    });

    function getScript(url, callback) {
        var head = document.getElementsByTagName('head')[0];
        var script = document.createElement('script');
        script.src = url;

        var done = false;
        // Attach handlers for all browsers
        script.onload = script.onreadystatechange = function() {
            if (!done && (!this.readyState ||
                    this.readyState == 'loaded' || this.readyState == 'complete')) {
                done = true;
                if (callback)
                    callback();

                // Handle memory leak in IE
                script.onload = script.onreadystatechange = null;
            }
        };

        head.appendChild(script);

        // We handle everything using the script element injection
        return undefined;
    }
    
    function ajaxEdit(bookId,price){
    	$.ajax({
            url: "/myssm/book/updateBook",
            dataType : "json",
            type: "get",
            data: {
            	"bookId":bookId,
            	"price":price
            },
            success:function(response){
            	alert(response.message);
            }
		});
    }
</script>
</body>
</html>