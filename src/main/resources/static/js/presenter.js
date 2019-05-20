class Presenter {
	constructor(view, model) {
		this.view = view;
		this.model = model;
		this.observer = new Observer();
	}
	
	init() {
		this.model.setObserver(this.observer);
		this.observer.register('makeSortedWord', this.changeWords, this);
	}
	
	makeSortedWords(url, type, division) {
		this.model.makeSortedWords(url, type, division);
	}
	
	changeWords(words) {
		this.view.changeWords(words.quotientWords, words.remainderWords);
	}
}