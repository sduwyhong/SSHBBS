
		function changeCategoryImg(){
			$("#categoryImgDiv").removeAttr("hidden");
		}

		function changeSculpture(){
			$("#userSculptureDiv").removeAttr("hidden");
		}

	  	function save(){
	  		editor.updateElement(); //非常重要的一句代码
	        //前台验证工作
	        //提交到后台
	        $("#detailForm").submit();
	    }
	  	
	  	function submitRegistForm(){
	  		$("#regist_form").submit();
	  	}
	  	
	    var name = false;
	    var repwd = false;
	    var phone = false;
	    var mail = false;
	    function submitCheck(){
	    	window.setTimeout(function(){
	    		if(name&&repwd&&phone&&mail){
	    			$("#regist_submit").removeAttr("disabled");
	    		}else{
	    			$("#regist_submit").attr("disabled","disabled");
	    			submitCheck();
	    		}
	    	},50);
	    }
	    function initBind(){
	    	
	    	$("#reloadVC").bind("click",function(){
	    		//js里的url地址要以“/项目名”开头
	    		$("#VCimgdiv>img").attr("src","/SSHBBS/user/userAction_showVC?"+Math.random()+"'/>");
	    	});
	    	$("#emailText").bind("blur",function(){
	    		var email = $(this).val();
	    		var parentTr = $(this).parent().parent();
	    		var td = $("<td/>");
	    		td.attr("nowrap","nowrap");
	    		if(parentTr.children().length>=3){
    				parentTr.children().last().remove();
    			}
	    		var reg = /^(.)*@(.)*.com$/;
	    		if(!reg.test(email)){
		    		td.text("邮箱不合法！");
	    			td.css("color","#FF0000");
	    			parentTr.append(td);
	    			mail = false;
	    			submitCheck();
	    		}else{
	    			td.text("邮箱合法！");
	    			td.css("color","#32CD32");
	    			parentTr.append(td);
	    			mail = true;
	    		}
	    	});
	    	$("#telephoneText").bind("blur",function(){
	    		var tel = $(this).val();
	    		var parentTr = $(this).parent().parent();
	    		var td = $("<td/>");
	    		td.attr("nowrap","nowrap");
	    		if(parentTr.children().length>=3){
	    			parentTr.children().last().remove();
	    		}
	    		var reg = /^1[0-9]{10}$/;
	    		if(!reg.test(tel)){
	    			td.text("手机号码不合法！");
	    			td.css("color","#FF0000");
	    			parentTr.append(td);
	    			phone = false;
	    			submitCheck();
	    		}else{
	    			td.text("手机号码合法！");
	    			td.css("color","#32CD32");
	    			parentTr.append(td);
	    			phone = true;
	    		}
	    	});
	    	$("#rePasswordText").bind("blur",function(){
	    		var pwd = $("#passwordText").val();
	    		var rePwd = $(this).val();
	    		var parentTr = $(this).parent().parent();
	    		var td = $("<td/>");
	    		td.attr("nowrap","nowrap");
	    		if(parentTr.children().length>=3){
	    			parentTr.children().last().remove();
	    		}
	    		if(pwd!=rePwd){
	    			td.text("密码不正确！");
	    			td.css("color","#FF0000");
	    			parentTr.append(td);
	    			repwd = false;
	    			submitCheck();
	    		}else{
	    			td.text("密码正确！");
	    			td.css("color","#32CD32");
	    			parentTr.append(td);
	    			repwd = true;
	    		}
	    	});
	    	$("#usernameText").bind("blur",function(){
	    		var text = $(this);
	    		var username = text.val();
	    		var parentTr = text.parent().parent();
	    		var td = $("<td/>");
	    		td.attr("nowrap","nowrap");
	    		if(parentTr.children().length>=3){
    				parentTr.children().last().remove();
    			}
	    		if(username==''){
	    			td.text("请输入用户名！");
	    			td.css("color","#FF0000");
	    			parentTr.append(td);
	    		}else{
	    			$.post(
	    					"/SSHBBS/user/userAction_checkUsername",
	    					{
	    						"username":username
	    					},
	    					function(data){
	    						var jsonData = eval("("+data+")");
	    						for(var key in jsonData){
	    							if(jsonData[key]=='input again'){
	    								td.text("用户名已存在！");
	    								td.css("color","#FF0000");
	    				    			parentTr.append(td);
	    				    			name = false;
	    				    			submitCheck();
	    							}else{
	    								td.text("用户名可用！");
	    								td.css("color","#32CD32");
	    				    			parentTr.append(td);
	    								name = true;
	    							}
	    						}
	    					}
	    			);
	    			submitCheck();
	    		}
	    	});
	    	$("#selectProvince").bind("change",function(){
	    		$.post(
	    			"/SSHBBS/user/userAction_city",
	    			{
	    				"id":$(this).val()
	    			},
	    			function(data){
	    				//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
	    				//alert("data:"+data);
	    				var cities = eval("("+data+")");
	    				$("#selectCity>option").remove();
	    				//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
	    				for(var city in cities){
	    					var option = $("<option/>");
	    					option.attr("value",cities[city]);
	    					option.text(cities[city]);
	    					$("#selectCity").append(option);
	    				}
	    			}
	    		);
	    	});
	    	
	    	$("#dateText").datebox({
	    		required:true
	    	});
	    	$(".category_img").bind("mouseover",function(){
	    		$(this).css("border","1px solid #FF8C00");
	    	});
	    	$(".category_img").bind("mouseout",function(){
	    		$(this).css("border","");
	    	});
	    	$(".category_img").bind("click",function(){
	    		var id = $(this).children(".category_id").text();
	    		window.location.href="/SSHBBS/category/categoryAction_showArticleList.action?id="+id;
	    	});
	    	$("#save").bind("click",function(){
	    		save();
	    	});
	    	$(".article_title:not(#article_info_header .article_title)").bind("mouseover",function(){
	    		$(this).css("color","#FF0000");
	    	});
	    	$(".article_title").bind("mouseout",function(){
	    		$(this).css("color","");
	    	});
	    }
	    
	    function initAttr(){
	    	
	    	$("td").attr("nowrap","nowrap");
	    	
	    	var id = $(".category_id").text();
	    	$("#write_article").attr("href","/SSHBBS/article/articleAction_add?categoryId="+id);
	    }
	    
	    function initCSS(){
	    }
	    
	    function initText(){
	    	var string = $(".text_html").val();
	    	$(".article_text").text(string);
	    }
	    
	    var editor = null;
	  	function initCKEditor(){
			//参数‘content’是textarea元素的name属性值，而非id属性值
	  		editor = CKEDITOR.replace( 'content', {
	            customConfig:'${pageContext.request.contextPath }/ckeditor/myconfig.js',
	            on: {
	                instanceReady: function( ev ) {
	                    this.dataProcessor.writer.setRules( 'p', {
	                        indent: false,
	                        breakBeforeOpen: false,   //<p>之前不加换行
	                        breakAfterOpen: false,    //<p>之后不加换行
	                        breakBeforeClose: false,  //</p>之前不加换行
	                        breakAfterClose: false    //</p>之后不加换行7
	                    });
	                }
	            }
	        });
	  	}
	  	/*
	  	function initDatagrid(){
	  		$("#article").datagrid({
		  		columns:[[
					{"checkbox":true},
					{title:"题目",width:600,field:'title'},
					{title:"用户",width:100,field:'user.username'},
					{title:"发布时间",width:100,field:'publish_time'},
					{title:"点击数",width:100,field:'click_num'}
				]
				],
				fitColumns:true,
				url:"",
				toolbar:
					[{
						text:"发帖",
						iconCls: 'icon-edit',
						handler: function(){
							
							alert('发帖按钮');
						}
					}]
				,
				onLoadSuccess:function(){
				},
				loadMsg:"数据正在加载中....",
				method:"POST",
				pagination:true,
				rownumbers:true,
				singleSelect:true,
				striped:true
		  });
	  	}
	  	*/
		$(function(){
			
		  initBind();
		  initAttr();
		  initText();
		  initCSS();
		  //为啥放开头，datagrid无法执行？
		  initCKEditor();
		  
		});	