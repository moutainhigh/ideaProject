/**
 * Created by Yuan Zhicheng on 2015/6/1.
 */
define(function(require, exports, module) {

	function setTree(data) {
		for (var i = 0; i < data.length; i++) {
			$("<tr></tr>")
					.addClass("treegrid-" + data[i].id)
					.addClass("treegrid-parent-" + data[i].parentId)
					.appendTo($('.tree'))
					.html(
							"<td>"
									+ data[i].name
									+ "</td><td>"
									+ data[i].url
									+ "</td><td>"
									+ data[i].isShow
									+ "</td><td>"
									+ data[i].permission
									+ "</td>" +
									"<td class=\"text-right footable-visible footable-last-column\">" +
									" <button type=\"button\"  class=\"btn btn-success  btn-sm\"  > <i class=\"glyphicon glyphicon-plus\"></i>添加</button>" +
									"</td>");
			if (data[i].hasChild > 0) {
				setTree(data[i].appMenuList);
				console.log('data[i].hasChild==' + data[i].hasChild);
			}
		}
	}

	// 获取menu
	function GetAppMenu(options) {
		var data = [ {
			"createTime" : null,
			"updateTime" : null,
			"del" : 0,
			"page" : 1,
			"rows" : 10,
			"id" : "1",
			"name" : "首页",
			"url" : "http://www.baidu.com",
			"parentId" : "0",
			"sort" : 0,
			"level" : 1,
			"path" : "1",
			"description" : "1",
			"hasChild" : 1,
			"deep" : null,
			"change" : null,
			"isShow" : "1",
			"permission" : "1",
			"appMenuList" : [ {
				"createTime" : null,
				"updateTime" : null,
				"del" : 0,
				"page" : 1,
				"rows" : 10,
				"id" : "2",
				"name" : "2",
				"url" : "1",
				"parentId" : "1",
				"sort" : 0,
				"level" : 2,
				"path" : "1,2",
				"description" : "1",
				"hasChild" : 0,
				"deep" : null,
				"change" : null,
				"isShow" : "1",
				"permission" : "1",
				"appMenuList" : null
			} ]
		}, {
			"createTime" : null,
			"updateTime" : null,
			"del" : 0,
			"page" : 1,
			"rows" : 10,
			"id" : "3",
			"name" : "订单",
			"url" : "http://www.sina.com",
			"parentId" : "0",
			"sort" : 0,
			"level" : 1,
			"path" : "1",
			"description" : "1",
			"hasChild" : 1,
			"deep" : null,
			"change" : null,
			"isShow" : "1",
			"permission" : "1",
			"appMenuList" : [ {
				"createTime" : null,
				"updateTime" : null,
				"del" : 0,
				"page" : 1,
				"rows" : 10,
				"id" : "4",
				"name" : "订单详情",
				"url" : "www",
				"parentId" : "3",
				"sort" : 0,
				"level" : 1,
				"path" : "1,3",
				"description" : "1",
				"hasChild" : 1,
				"deep" : null,
				"change" : null,
				"isShow" : "1",
				"permission" : "1",
				"appMenuList" : [ {
					"createTime" : null,
					"updateTime" : null,
					"del" : 0,
					"page" : 1,
					"rows" : 10,
					"id" : "5",
					"name" : "订单增加",
					"url" : "www",
					"parentId" : "4",
					"sort" : 0,
					"level" : 3,
					"path" : "3,4,5",
					"description" : "1",
					"hasChild" : 0,
					"deep" : null,
					"change" : null,
					"isShow" : "1",
					"permission" : "1",
					"appMenuList" : null
				} ]
			} ]
		}, {
			"createTime" : null,
			"updateTime" : null,
			"del" : 0,
			"page" : 1,
			"rows" : 10,
			"id" : "76f58b8bf033497085a13e2d7d66897e",
			"name" : "13123",
			"url" : "123",
			"parentId" : "0",
			"sort" : 123,
			"level" : 2,
			"path" : "0,13,",
			"description" : "132",
			"hasChild" : 0,
			"deep" : null,
			"change" : null,
			"isShow" : "312321",
			"permission" : "123",
			"appMenuList" : null
		} ];

		

		for (var i = 0; i < data.length; i++) {
			var tr = $("<tr></tr>")
					.addClass("treegrid-" + data[i].id)
					.appendTo($('.tree'))
					.html(
							 "<td  name=\"name\" >" 
									+ data[i].name
									+ "</td><td>"
									+ data[i].url
									+ "</td><td>"
									+ data[i].isShow
									+ "</td><td>"
									+ data[i].permission
									+ "</td>" +
									"<td class=\"text-right footable-visible footable-last-column options \">" +
										" <button type=\"button\"  class=\"btn btn-success  btn-sm add \"  > <i class=\"glyphicon glyphicon-plus\"></i>添加</button>" +
										" <button type=\"button\"  class=\"btn btn-success  btn-sm  modify \"  > <i class=\"glyphicon glyphicon-plus\"></i>编辑</button>" +
									"</td>");
			if (data[i].hasChild > 0) {
				setTree(data[i].appMenuList);
				console.log('data[i].hasChild==' + data[i].hasChild);
			}
		}

		$('.tree').treegrid({
			'initialState' : 'collapsed',
			expanderTemplate : '<i class="treegrid-expander fa"></i>',
			expanderExpandedClass : 'fa fa-minus ',
			expanderCollapsedClass : 'fa fa-plus  '
		});
		
		//编辑事件
		$(".options").on("click",".modify",function(){
			
			var tr = $(this).parent().parent();
			var td = tr.find("td[name=name]");
			td.appendTo("<input  type=\"text\"></input>")
			
		})


	}

	exports.GetAppMenu = GetAppMenu;

});
