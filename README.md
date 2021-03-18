<h3>The Chuck Quotis Project</h3>


<h4>Short Description</h4>
<p>Chuck Quotis is a web application that allows users to browse and save lots of pre-existing Chuck Norris jokes provided by the following service: https://api.chucknorris.io/ .Users can also generate personal quotes and edit existing ones. The application incorporates a log-in and registration service, two different kinds of user roles - User and Administrator, and a simple posting and commenting feature. The application uses a REST based architecture. The front end layout uses Bootstrap, while all of the API calls and dynamic content generation mechanisms use a blend of JavaScript, JQuery and AJAX for the API calls. The back end of the project uses Java and Spring boot (with Spring Security configured). The persistence layer uses an ORM - Hibernate and H2 as a lightweight database solution.</p>
<h4>Implemented features:</h4>
<ol>
<li>Retrieve and visualise the set of quotes from the API Database</li>
<li>Filter the received quotes by category (There are only two types of categories according to the official API documentation - "funny" and "nerdy")</li>
<li>Generate a personalised quote. The quotes returned by this function have name placeholders and can be tailored to the specific needs of the end user. A user generated Name</li>
(consisting of a first name and last name ) can be inserted in the API call, and the returned response will include the specified names as a part of the quote</li>
<li>Generate a random quote</li>
<li>The user may pick between two alternate ways of visualising the results - Card view (default) and List view.</li>
<li>Responsive Web UI - optimized for smaller devices and ordinary desktop screens.  </li>
</ol>
