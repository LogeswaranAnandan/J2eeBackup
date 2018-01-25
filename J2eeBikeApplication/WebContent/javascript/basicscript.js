document.getElementById('user-name-container').addEventListener('click', toggleClass);
document.getElementsByTagName('body')[0].addEventListener('click', removeClass);

function toggleClass() {
	document.getElementById('logout-container').classList.toggle('display-none');
	document.getElementById('logout-container').classList.toggle('display-block');
}

function removeClass() {
	console.log(event.srcElement.id );
	console.log(event.srcElement.className);
	if((event.srcElement.id != 'user-name-container') && (event.srcElement.className != 'user-name')) {
		console.log('inside if');
		document.getElementById('logout-container').classList.remove('display-block');
		document.getElementById('logout-container').classList.remove('display-none');
		document.getElementById('logout-container').classList.add('display-none');
	}
}