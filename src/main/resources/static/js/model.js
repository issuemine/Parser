class Model {
	constructor() {
		this.observer;
	}
	
	setObserver(observer) {
		this.observer = observer;
	}
	
	makeSortedWords(url, type, division) {
		let requestUrl = "/parsing?url=" + url + "&type=" + type + "&division=" + division;
		let $this = this;
		$.ajax({
			"url": encodeURI(requestUrl),
			"method": "GET",
			"dataType": "json",
			"success": function(data) {
				$this.observer.notify('makeSortedWord', data);
			},
			"error": function(xhr) {
				if (xhr.status === 400) {
					alert('Bad request');
				} else if (xhr.status === 500) {
					alert('Bad gateway');
				}
				
			}
		})
	}
}