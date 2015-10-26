<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="style.css">
<title>Quiz Questions</title>
</head>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	var quizObj;
	var counter = 0;
	var loadedQuiz = false;
	var answerObj = [];
	var answerString = "";
	
	$(document).ready(function() {
		if (loadedQuiz == false) {
			getQuiz();
		}		

		
		//$("#btnSubmit").click(submitAnswer); //register event listener
		$("#formQuestion").submit(doNext); //form event listener
		$("#btnPrev").click(doPrev);
	}) //document.ready

	function getQuiz() {
		$.ajax({
			url: "Rest.html?quiz",
			data: "",
			type: "GET",
			dataType: "json",
			success: function(json) {
				//console.log("success ajax!");
				loadQuizObj(json);
				
				//$("#correct").html(text);		          
			},
			error: function(xmlhttp, status) {
				console.error("ajax fail");
				console.error(xmlhttp);
				console.error(status);
			}
		
		}) //ajax
	} //getQuiz
	
	function uploadQuiz() {
		$.ajax({
			url: "Results.html?answers=" + encodeURI(answerString),
			//contentType: "application/text",
			data: "",
			type: "GET",
			dataType: "text",
			success: function(text) {
				console.log("success ajax!");
				console.log("text=" + text);
				window.location.href = text;		          
			},
			error: function(xmlhttp, status) {
				console.error("ajax fail");
				console.error(xmlhttp);
				console.error(status);
			}
		
		}) //ajax
	} //upload Quiz
	
	function loadQuizObj(jsonQuiz) {		
		quizObj = jsonQuiz;
		buildQuizDom(quizObj);
		console.log(quizObj);
		loadedQuiz = true;
	} //loadQuizObj
	
	function buildQuizDom() {
		updateQuestionList();
		setQuestionTitle();
		updateProgress();
		updateSubmit();
		updatePrev();
	} //buildQuizDom
	
	function setQuestionTitle() {
		//set header text
		var header = document.getElementById("h1Question");
		header.innerHTML = "Question # " + (counter + 1) + " of " + quizObj.numberOfQuestions;
		//console.log(header);
		//$("#h1Question").html = "Question # " + (counter) + " of " + quizObj.numberOfQuestions;
		
		//set Question text
		var liSpaceNode = createSpaceNode();
		
		var liTitleNode = document.createElement("li");
		liTitleNode.innerHTML = "<strong>" + quizObj.name + " : " + quizObj.questions[counter].value + " </strong>";
		
		$("#questionsUL").prepend(liTitleNode, liSpaceNode);
	}  //setQuestionTitle
	
	//build list of answers
	function updateQuestionList() {
		$("#questionsUL").empty();
		var len = quizObj.questions[counter].answers.length;
		for (var i = 0; i < len; i++) {
			//console.log(quizObj.questions[counter].answers[i].value);
			var liAnswer = document.createElement("li");
			var liInput = document.createElement("input");
			var liSpaceNode = createSpaceNode();
			
			liInput.setAttribute("type", "radio");
			liInput.setAttribute("name", "ans");
			liInput.setAttribute("value", quizObj.questions[counter].answers[i].value);
			if (i == len - 1){
				liInput.setAttribute("autofocus", "true");
			}
			
			liAnswer.appendChild(liInput);
			liAnswer.innerHTML += quizObj.questions[counter].answers[i].value;
			liAnswer.appendChild(liSpaceNode);
			$("#questionsUL").prepend(liAnswer);
		}
	} //updateQuestionList
	
	function updateSubmit() {
		if (counter == quizObj.numberOfQuestions - 1) {
			$("#btnSubmit")[0].value = "SUBMIT ANSWERS";
			//var btn = document.getElementById("btnSubmit");
			//btn.value = "SUBMIT QUIZ";
		}
		else {
			$("#btnSubmit")[0].value = "Next Question";
		}
	}
	
	function updatePrev() {
		if (counter == 0) {
			$("#btnPrev").hide();
		} else {
			$("#btnPrev").show();
		}
	} //updatePrev
	
	function updateProgress() {
		$("#quizProgress")[0].value = (counter + 1) / quizObj.numberOfQuestions;
	} //updateProgress
	
	function createSpaceNode() {
		var liSpaceNode = document.createElement("li");//'<li>&nbsp;</li>';
		liSpaceNode.innerHTML = "&nbsp;";
		return liSpaceNode;
	} //createSpaceNode
	
	function doPrev() {
		counter--;
		buildQuizDom();
	} //doPrev
	
	function doNext() {
		updateAnswerObj();
		if (counter == quizObj.numberOfQuestions -1) {
			buildAnswerString();
			uploadQuiz();
		}

		if (counter < (quizObj.numberOfQuestions - 1)) {
			counter++;
		}
		else {
			console.log("submit quiz results");
		}
		console.log("counter = " + counter);
		buildQuizDom();
		return false;
	} //doNext
	
	function updateAnswerObj() {
		var quesId = counter + 1;
		var answered = $("input:radio[name=ans]:checked" ).val();
		console.log("answered: " + answered);
		if (answered == undefined) {
			answered = "(You did not answer this question)";
		}
		//console.log("given answer = " + answered);
		answerObj[counter] = answered;
		console.log(answerObj);
		
	} //updateAnswerObj
	
	function buildAnswerString() {
		for (var i = 0; i < answerObj.length; i++) {
			answerString += answerObj[i] + "|";
		}
		console.log("answers= " + answerString);
	} //buildAnswerString

</script>
<body>

	<h1 id="h1Question"></h1>
	
	<form id="formQuestion" method="POST" onsubmit="return false;" >
	
		<ul id="questionsUL">
<!-- 		<li>(Q.ID = 1) <strong>What is the capital of Alabama?</strong></li> -->
<!-- 		<li>&nbsp;</li> -->
		
<!-- 		<li><input type="radio" name="ans" value="Birmingham" autofocus tabindex="0" /> Birmingham</li> -->
<!-- 		<li>&nbsp;</li> -->
		
<!-- 		<li><input type="radio" name="ans" value="Montgomery"  tabindex="1" /> Montgomery</li> -->
<!-- 		<li>&nbsp;</li> -->
		
<!-- 		<li><input type="radio" name="ans" value="Mobile"  tabindex="2" /> Mobile</li> -->
<!-- 		<li>&nbsp;</li> -->
		
<!-- 		<li><input type="radio" name="ans" value="Huntsville"  tabindex="3" /> Huntsville</li> -->
<!-- 		<li>&nbsp;</li> -->
		</ul>
		<ul>
		 
		<li><input id="btnPrev" class="btnSubmit" type="button" value="Prev Question" /> <input id="btnSubmit" class="btnSubmit" type="submit" value="Next Question" /></li>
		<li>&nbsp;</li> 
		<li>Progress: <progress id="quizProgress" title="Quiz Progress" value="0.25"></progress></li>
		</ul>
	</form>

</body>
