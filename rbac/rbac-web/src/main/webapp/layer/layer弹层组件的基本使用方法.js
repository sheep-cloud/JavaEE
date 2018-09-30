           
			// http://www.layui.com/doc/modules/layer.html#use

			// 提示:
			
			layer.msg(提示信息, {time:1000, icon:5, shift:6}, 回调方法)
			layer.alert(提示信息, function(index){
			    // 回调方法
			    layer.close(index)
			})

			// 询问：
			
			layer.confirm("询问信息",  {icon: 3, title:'提示'}, function(cindex){
			    layer.close(cindex)
			}, function(cindex){
			    layer.close(cindex)
			})
			
			// 加载
			var loadingIndex = layer.msg('处理中', {icon: 16})
			// ...
			layer.close(loadingIndex)
			
			var index = layer.load(2, {time: 10*1000})
			layer.close(index)