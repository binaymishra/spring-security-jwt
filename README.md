Simple JWT base authentication using Spring Security.
=====================================================

1) How to run ?

	i) Download / clone.

	ii) Build 
		
		./gradlew 
		
	iii) Run
		
		sh run.sh
		
	iv) Generate Toke using endpoint
	
	  http://localhost:3000/token
	  
	  Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6IlVTRVIsIEFETUlOIiwidXNlcm5hbWUiOiJ1c2VyIn0.4nYziaBB6isRu9fIzHw_rKi8OQde99ku3TNaKzDf-Ww
	  
	v) Use this token in http header.
	
		Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6IlVTRVIsIEFETUlOIiwidXNlcm5hbWUiOiJ1c2VyIn0.4nYziaBB6isRu9fIzHw_rKi8OQde99ku3TNaKzDf-Ww
		
		and Access endpoint 
		
		http://localhost:3000/api