class View {
	constructor() {
		this.presenter;
		this.makeSortedWordBtn = document.getElementById("make_sorted_word_btn");
	}
	
	setPresenter(presenter) {
		this.presenter = presenter;
	}
	
	init() {
		let $this = this;
		this.presenter.init();
		this.makeSortedWordBtn.onclick = function(event) {
			let url = document.getElementById("url").value;
			let type = document.getElementById("type").value;
			let division = document.getElementById("division").value;
			document.getElementById("quotient_words").innerHTML = "";
			document.getElementById("remainder_words").innerHTML = "";
			$this.presenter.makeSortedWords(url, type, division);
		}
	}
	
	changeWords(quotientWords, remainderWords) {
		document.getElementById("quotient_words").innerHTML = quotientWords;
		document.getElementById("remainder_words").innerHTML = remainderWords;
	}
	
}