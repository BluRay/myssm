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
<script src="../js/layer.js"></script>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-table.css">
<link rel="stylesheet" href="../css/bootstrap-editable.css">
<link rel="stylesheet" href="../css/index.css">
<link rel="stylesheet" href="../css/layer.css">
<title>Book list</title>
<script type="text/javascript">
var company = [{value:"长沙市出租车公司",text:"长沙市出租车公司"},{value:"2",text:"销售部2"},{value:"3",text:"行政部"}];
</script>
</head>
<body style="font-family: 'Microsoft YaHei';">
<div class="container" style="height:300px">
    <h1></h1>
    <div id="toolbar">
        <button id="btn_add" class="btn btn-success">
            <i class="glyphicon glyphicon-ok"></i> 增加车辆信息
        </button>
    </div>
    <table id="table" data-toolbar="#toolbar" data-search="true" data-show-refresh="true"
           data-show-toggle="true" data-show-columns="true" data-show-export="true" data-detail-view="false"
           data-detail-formatter="detailFormatter" data-minimum-count-columns="2" data-show-pagination-switch="true"
           data-pagination="true" data-id-field="id" data-page-list="[50, 100, 200, 500, ALL]"
           data-show-footer="false" data-side-pagination="server"
           data-url="/myssm/car/getCarList" data-response-handler="responseHandler">
    </table>
    
    <div id="addLayer" class="box-body" style="display: none; padding: 10px;">
    	<form action="#">
    		<table>
    		<tr>
    		<td><label class="control-label" style="width: 100px">订单名称：<i class="fa icon-question hide"></i></label></td>
    		<td><input type="text" name="order_name" style="height: 30px;" class="form-control" placeholder="订单名称" /></td>
    		</tr>
    		</table>
    	</form>
    </div>
    
</div>
<script>
    var $table = $('#table'),$remove = $('#remove'),selections = [];

    function initTable() {
        $table.bootstrapTable({
            height: getHeight(),
            columns: [
            [
                {
                	field: 'id',title: '车辆编号',width:'60px',align: 'center',valign: 'middle',
                    sortable: true,footerFormatter: totalTextFormatter
                },{
                    field: 'chepai',title: '车牌',width:'120px',sortable: true,
                    editable: {
                        type: 'text',title: '车牌',
                        validate: function (value) {
                            value = $.trim(value);
                            if (!value) {
                                return 'This field is required';
                            }
                            var data = $table.bootstrapTable('getData'),
                                index = $(this).parents('tr').data('index');
                            console.log("---->data : id = " + data[index].id + " newvalue = " +value);
                            ajaxEdit("jjq_cepai",data[index].id,value.replace('湘 A·',''));
                            return '';
                        }
                    },
                    footerFormatter: totalNameFormatter,align: 'center'
                }, {
                    field: 'gongsi',title: '公司',width:'220px',sortable: true,align: 'center',
                    editable: {
                        type: 'text',title: '公司',emptytext:'未填写公司',
                        validate: function (value) {
                            value = $.trim(value);
                            if (!value) {
                                return 'This field is required';
                            }
                            var data = $table.bootstrapTable('getData'),
                                index = $(this).parents('tr').data('index');
                            console.log("---->data : id = " + data[index].id + " newvalue = " +value);
                            ajaxEdit("jjq_coid",data[index].id,value);
                            return '';
                        }
                    },
                    footerFormatter: totalPriceFormatter
                },{
                    field: 'jijiaqihao',title: '计价器号',width:'100px',
                    editable:{
                    	emptytext:'未填写计价器号',
                    	validate: function (value) {
                            value = $.trim(value);
                            var data = $table.bootstrapTable('getData'),
                                index = $(this).parents('tr').data('index');
                            ajaxEdit("jjq_no",data[index].id,value);
                            return '';
                        }
                    },
                    footerFormatter: totalNameFormatter,align: 'center'
                },{
                    field: 'siji',title: '司机编号',width:'80px',
                    editable:{
                    	emptytext:'未填写司机',
                    	validate: function (value) {
                            value = $.trim(value);
                            var data = $table.bootstrapTable('getData'),
                                index = $(this).parents('tr').data('index');
                            ajaxEdit("jjq_driname",data[index].id,value);
                            return '';
                        }
                    },
                    footerFormatter: totalNameFormatter,align: 'center'
                }, {
                    field: 'dianhua',title: '联系电话',width:'100px',
                    editable:{
                    	emptytext:'未填写电话',
                    	validate: function (value) {
                            value = $.trim(value);
                            var data = $table.bootstrapTable('getData'),
                                index = $(this).parents('tr').data('index');
                            ajaxEdit("jjq_dritel",data[index].id,value);
                            return '';
                        }
                    },
                    footerFormatter: totalNameFormatter,align: 'center'
                },{
                    field: 'anzhuangriqi',title: '起始日期',width:'100px',sortable: true,
                    editable:{
                    	emptytext:'未填写日期',
                    	validate: function (value) {
                            value = $.trim(value);
                            var data = $table.bootstrapTable('getData'),
                                index = $(this).parents('tr').data('index');
                            ajaxEdit("jjq_sdate",data[index].id,value);
                            return '';
                        }
                    },
                    footerFormatter: totalNameFormatter,align: 'center'
                },{
                    field: 'beizhu',title: '备注',
                    editable:{
                    	emptytext:'未填写备注',
                    	validate: function (value) {
                            value = $.trim(value);
                            var data = $table.bootstrapTable('getData'),
                                index = $(this).parents('tr').data('index');
                            ajaxEdit("tel_memo",data[index].id,value);
                            return '';
                        }
                    },
                    footerFormatter: totalNameFormatter,align: 'center'
                }, {
                    field: 'operate',title: '操作',width:'40px',align: 'center',
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
            /* $table.bootstrapTable('remove', {
                field: 'id',
                values: [row.id]
            }); */
            ajaxDel(row.id);
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
    	$("#btn_add").click(function () {
    		console.log("-->btn_add")
    		layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "编辑订单",
				area : [ '400px', '240px' ],
				shade : 0,
				shadeClose : true,
				content : jQuery("#addLayer"),
				btn : [ '确定' ,	'取消'],
				btn1 : function(index) {
					console.log('btn1')
				},
				btn2 : function(index) {
					console.log('btn2')
				}
			});
    		
    	})
    	
    	
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
    
    function ajaxEdit(param,id,value){
    	$.ajax({
            url: "/myssm/car/updateCar",
            dataType : "json",
            type: "get",
            data: {
            	"param":param,
            	"id":id,
            	"value":value
            },
            success:function(response){
            }
		});
    }
    
    function ajaxDel(id){
    	//$table.bootstrapTable('refresh');
    	if(confirm("确定删除数据吗？删除后不可恢复！")){
    		$.ajax({
                url: "/myssm/car/deleteCar",
                dataType : "json",
                type: "get",
                data: {
                	"id":id
                },
                success:function(response){
                	//alert(response.message);
                	$table.bootstrapTable('refresh');
                }
    		});
    	}
    }
    
</script>
</body>
</html>