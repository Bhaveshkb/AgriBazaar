/**
 * 
 */
 
 $('.datepicker').datepicker({
		todayBtn : "linked",
		language : "it",
		autoclose : true,
		format : 'yyyy-mm-dd',
		startDate : new Date()
	});

	function validation() {
		var pname = document.getElementById('pname').value;
		var price = document.getElementById('minPrice').value;

		if (pname == "") {
			document.getElementById('namee').innerHTML = "**Please write product name in the Box**";
			return false;
		}
		if (!isNaN(pname)) {
			document.getElementById('namee').innerHTML = "**Please write Alphabate only in the Box**";
			return false;
		} else {
			document.getElementById('namee').innerHTML = ""
		}

		if (price == "") {
			document.getElementById('pricee').innerHTML = "**Please write price in the Box**";
		}

		if (isNaN(price)) {
			document.getElementById('pricee').innerHTML = "**Please write Number only in the Box**";
			return false;
		} else {
			document.getElementById('pricee').innerHTML = ""
		}
	}