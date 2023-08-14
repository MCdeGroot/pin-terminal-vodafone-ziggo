# pin-terminal-vodafone-ziggo
VodafoneZiggo Tech case

In this repository you find my solutions for the vodafone tech case. Her i will describe shortly how i started this case and how I executed it. 
First of all I created a list of aspects from the assignment that weren't clear to me, and I proceeded to research them on the internet. 
For instance, WireMock I visited the website and went through the documentation to comprehend its functionality and how to implement it into my project.

After that, I formulated a step-by-step plan to initiate the implementation of the assignment. 


1. Requirements and Preparation:

- Set up a Java environment
- Find out working of HTTP requests and responses. (used to Springboot so far)
- Add WireMock library.

2. MockServer
- Create a MockServer handler to start a mock server and configure WireMock stubs

3. Pin terminal activator:
- Create a Java class for the activation named for example ActivatorPinTerminal.
- Implement a method to accept input parameters (customer ID and MAC address).
- Use an HTTP client to send a POST request to the /activate endpoint with the input parameters

4. Sending Request to the Southbound System:

- Use WireMock stubs to simulate responses.
- Set up tests to test the MockServer by implementing the WireMock Server I created.
- Run the test and find out to see if the Activator is corresponding well with the WireMockServer.

5. Logging Records:

- Use a logging library (log4j) to record logs with relevant information.

