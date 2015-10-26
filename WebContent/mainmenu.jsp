<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
	/* Display with href */
  	ul#mainMenu li p:nth-of-type(2) {  
  		display:none;  
  	} 
	
 	ul#mainMenu li: p:nth-of-type(1) a:active { 
 		background-color: red; 
 		 
 	} 
 	
 	ul#mainMenu li p:nth-of-type(2):target {  
  		font-weight:bold;
  		display: block; 
  	}
  	
	/* Display with hover */
  	ul#mainMenu li p:nth-of-type(2) {  
  		display:none;  
  	}  
	
  	ul#mainMenu li:hover p {  
  		/* background-color: red;*/ 
  		display:block;  
  	}     	
</style>
<h2>Main Menu</h2>
		<ul id="mainMenu">
			<li>
				<p><a href="#quizApp">Quiz App</a></p>
				<p id="quizApp"><a href="quiz.html?showQuizzes=true">View Quizzes:</a><br/>

			</li> 
		</ul>


</html>