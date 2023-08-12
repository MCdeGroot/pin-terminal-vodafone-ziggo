# pin-terminal-vodafone-ziggo
VodafoneZiggo Tech case

In this repository you find my solutions for the vodafone tech case. Her i will describe shortly how i started this case and how I executed it. 
Next, I created a list of aspects from the assignment that weren't clear to me, and I proceeded to research them on the internet. 
For instance, WireMock I visited the website and went through the documentation to comprehend its functionality and how to implement it into my project.

After that, I formulated a step-by-step plan to initiate the implementation of the assignment. 


1. Requirements and Preparation:

- Set up a Java environment
- Find out working of HTTP requests and responses. (used to Springboot so far)
- Add WireMock library.

2. Program Structure:

- Create a Java class for the activation named for example ActivatorPinTerminal.

3. Accepting Input:

- Implement a method to accept input parameters (customer ID and MAC address).

4. Sending Request to the Southbound System:

- Use an HTTP client to send a POST request to the /activate endpoint with the input parameters.

5. Processing Responses:

- Receive and interpret the HTTP response from the southbound system.
- Use WireMock stubs to simulate responses.

6. Sending Status Back to the Orchestrator:

- Depending on the response from the southbound system, determine which status ("ACTIVE", "INACTIVE") should be sent back to the orchestrator.

7. Logging Records:

- Use a logging library (java.util.logging) to record logs with relevant information.

