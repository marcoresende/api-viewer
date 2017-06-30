function promptHost(){
  			var host = prompt("Informe o host. Ex: 192.168.1.10:8080");
  			
  			changeHost(null, host, null);
  		}
  
  		//Altera host de chamada do servico ao inicializar
	  	function changeSwaggerOnInit (scheme,host,path) {
		  var newspec = ui.spec().toJSON().resolved
		  if(newspec){
			  observer.disconnect();
			  if(!newspec.host){
				  newspec.scheme = [scheme] || newspec.scheme 
				  newspec.host = host || newspec.host
				  newspec.basePath = path || newspec.basePath
				  ui.getStore().dispatch({type:'set_scheme',payload: {scheme: newspec.scheme[0]}})
				  ui.getStore().dispatch({type:'spec_update_resolved',payload:newspec})
			  }
			  if(!window.createdBtn){
				  var btn = htmlToElement('<button id="btnHost" class="btn" onclick="promptHost()"><span>Host</span></button>');
				  var wrapper = document.getElementsByClassName('btn authorize unlocked')[0];
				  
				  insertAfter(wrapper, btn);
				  window.createdBtn = true;
			  }
			  if(!window.home){
				  var home = htmlToElement('<a href="/api-viewer" title="Home" class="link"><img height="30" width="30" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAqRJREFUeNrEVz1s00AUfnGXii5maMXoEUEHVwIpEkPNgkBdMnQoU5ytiKHJwpp2Q2JIO8DCUDOxIJFIVOoWZyJSh3pp1Q2PVVlcCVBH3ufeVZZ9Zye1Ay86nXV+ue/9fO/lheg/Se02X1rvksmbnTiKvuxQMBNgBnN4a/LCbmnUAP6JV58NCUsBC8CuAJxGPF47OgNqBaA93tolUhnx6jC4NxGwyOEwlccyAs+3kwdzKq0HDn2vEBTi8J2XpyMaywNDE157BhXUE3zJhlq8GKq+Zd2zaWHepPA8oN9XkfLmRdOiJV4XUUg/IyWncLjCYY/SHndV2u7zHr3bPKZtdxgboJOnthvrfGj/oMf3G0r7JVmNlLfKklmrt2MvvcNO7LFOhoFHfuAJI5o6ta10jpt5CQLgwXhXG2YIwvu+34qf78ybOjWTnWwkgR36d7JqJOrW0hHmNrKg9xhiS4+1jFmrxymh03B0w+6kURIAu3yHtOD5oaUNojMnGgbcctNvwdAnyxvxRR+/vaJnjzbpzcZX+nN1SdGv85i9eH8w3qPO+mdm/y4dnQ1iI8Fq6Nf4cxL6GWSjiFDSs0VRnxC5g0xSB2cgHpaseTxfqOv5uoHkNQ6Ha/N1Yz9mNMppEkEkYKj79q6uCq4bCHcSX3fJ0Vk/k9siASjCm1N6gZH6Ec9IXt2WkFES2K/ixoIyktJPAu/ptOA1SgO5zqtr6KASJPF0nMV8dgMsRhRPOcMwqQAOoi0VAIMLAEWJ6YYC1c8ibj1GP51RqwzYwZVMHQuvOzMCBUtb2tGHx5NAdLKqp5AX7Ng4d+Zi8AGDI9z1ijx9yaCH04y3GCP2S+QcvaGl+pcxyUBvinFlawoDQjHSelX8hQEoIrAq8p/mgC88HOS1YCl/BRgAmiD/1gn6Nu8AAAAASUVORK5CYII=" alt="Swagger UX" /><span>Home</span></a>');
				  //var home = htmlToElement('<a href="/" title="Home" class="link"><span>Home</span></a>');
				  var parent = document.getElementsByClassName('topbar-wrapper')[0];
				  //topBar = document.getElementsByClassName('topbar-wrapper')[0].childNodes[0];
				  var child = parent.childNodes[0];
				  parent.removeChild(child);
				  insertAfter(parent.childNodes[0], home);
				  window.home = true;
			  }
		  }
  		}
  		
	  	function changeHost (scheme,host,path) {
	  		var newspec = ui.spec().toJSON().resolved
			newspec.scheme = [scheme] || newspec.scheme 
			newspec.host = host || newspec.host
			newspec.basePath = path || newspec.basePath
			ui.getStore().dispatch({type:'set_scheme',payload: {scheme: newspec.scheme[0]}})
			ui.getStore().dispatch({type:'spec_update_resolved',payload:newspec})
		}
	  	
	  	function insertAfter(referenceNode, newNode) {
	  	    referenceNode.parentNode.insertBefore(newNode, referenceNode);
	  	}
	  	
	  	function htmlToElement(html) {
	  	    var template = document.createElement('template');
	  	    template.innerHTML = html;
	  	    return template.content.firstChild;
	  	}
	  	