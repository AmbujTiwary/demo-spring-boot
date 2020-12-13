Spring boot project
---------------------------

Software version and details:

Java.version : 11
Spring boot version: 2.4.0

Steps to run :
1. Import project in sts ide as existing project
2. Project root folder: demo-vmware will be created
3. Run this project as java application. 
4. project will be deployed and accessible on tomcat port 8080. 
5. Open postman and send a get request on url : localhost:8080/api/
	If server is up and running then server reponse will be as follows:
	{
    "result": "connected with server"
	}

Basic operations to run:
--------------------------------
Enter the following url url and expect the respective response as follows:

1. Post request to generate the data	
	Request: 
	
	    post url : localhost:8080/api/generate/
	    data: {
			"goal": 10,
			"step": 2
		}
		
	Response: 
		{
			"task": "e1d5566b-47ec-495a-a0fc-7b453b2a671d"
		}

2. get request to retrieve the status of the task:	/api/tasks/{UUID of the task}/status
	
	Request: 
		get url: localhost:8080/api/tasks/e1d5566b-47ec-495a-a0fc-7b453b2a671d/status (Replace with generated task)
	Response: 
		1. If hit the url between 0 to (10-30 sec / depends on random no generator)
		{
			"result": "IN_PROGRESS"
		}
	
		2. If hit the url after (10-30 sec / depends on random no generator)
		{
			"result": "SUCCESS"
		}
		
3. Get request to retrieve the generated number: /api/tasks/{Task UUID
of the task}?action=get_numlist
	
	Request:
		get url: localhost:8080/api/tasks/0d1fcd35-fc8d-411c-be87-084a432f5ab1/?action=get_numlist //Replace with generated uid
	Response: 
		{
			"result": "[[10, 8, 6, 4, 2, 0]]"
		}

4.  Post request to generate data in bulk :  localhost:8080/api/bulkGenerate

	Request: 
		Post url: localhost:8080/api/bulkGenerate
		data: 
			[{
				"goal": 10,
				"step": 2
			},
			{
				"goal": 10,
				"step": 3
			}]
	Response:
		{
			"task": "bcd40a97-d92a-421b-a380-3668fde5254e"
		}
		
5.  Get request to retrieve the generated number in bulk : /api/tasks/{Task UUID
of the task}?action=get_numlist

	Request: 
		Get url: localhost:8080/api/tasks/bcd40a97-d92a-421b-a380-3668fde5254e/?action=get_numlist  //replace with generated uid
	Response:
		{
			"result": "[[10, 7, 4, 1], [10, 8, 6, 4, 2, 0]]"
		}
