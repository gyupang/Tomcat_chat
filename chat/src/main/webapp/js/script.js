function send(form) {
	form.msg.value = form.temp.value;
	form.temp.value='';
	form.temp.focus();
	return true;
}