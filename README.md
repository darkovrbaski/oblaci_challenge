# oblaci_challenge

Solution to the challenge in 5 days in the clouds 2022

<br>

<a name="readme-top"></a>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#build">Build</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
  </ol>
</details>

<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these steps.

### Prerequisites

* Java 17
* PostgreSQL
* NPM
* Angular CLI 14.2.9.
* Docker


### Build

**Spring Boot application**

- METHOD 1: Running in IDE

	Set in `application.yaml` Postgres DB `username` and `password` and
		create database named `CryptoExchangeDb` in your local Postgres DB

	Open Spring Boot application in your IDE located in `/crypto-exchange/`
		Execute the `main` method in the `me.darko.cryptoexchange.CryptoExchangeApplication` class.
- METHOD 2: Running with Docker
	
	From project directory, start up application by running 
		`docker compose up --build`
	
- Application will be available at

	<a href="http://localhost:8080/">http://localhost:8080/</a>

- Check running application at Swagger UI
	
	http://localhost:8080/swagger-ui/index.html

**Angular application**

1. Go to directory ./angular-app
   ```sh
   cd ./angular-app/
   ```
3. Install Angular dependencies
   ```sh
   npm install
   ```
4. Run Angular application
   ```sh
   npm start
   ```
5. Open application in browser and navigate
<a href="http://localhost:4200/">http://localhost:4200/</a>


<p align="right">(<a href="#readme-top">back to top</a>)</p>

