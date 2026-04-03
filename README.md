# GitHub OAuth2 Connector (Spring Boot)

## 📌 Overview

This is a Spring Boot backend application that integrates with GitHub using OAuth2 authentication.
After login, users can fetch repositories, create issues, and list issues from their GitHub account.

---

## 🚀 Features

* OAuth2 Login with GitHub
* Fetch authenticated user repositories
* Create issue in a repository
* List issues of a repository
* Secure API integration using access token

---

## 🛠️ Tech Stack

* Java
* Spring Boot
* Spring Security OAuth2 Client
* WebClient
* GitHub REST API

---

## 🔐 OAuth2 Flow (Simple Explanation)

1. User opens login URL
2. Redirected to GitHub login page
3. User grants permission
4. GitHub sends authorization code
5. Spring Boot exchanges code → access token
6. Token is used to call GitHub APIs

---

## ⚙️ Setup Instructions

### 1. Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/github-oauth2-connector.git
cd github-oauth2-connector
```

---

### 2. Create GitHub OAuth App

Go to:
GitHub → Settings → Developer Settings → OAuth Apps → New OAuth App

Fill:

* Application Name: Any name (not starting with GitHub)
* Homepage URL:

```
http://localhost:8080
```

* Authorization Callback URL:

```
http://localhost:8080/login/oauth2/code/github
```

After creation, copy:

* Client ID
* Client Secret

---

### 3. Configure application.yml

Update:

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          github:
            client-id: YOUR_CLIENT_ID
            client-secret: YOUR_CLIENT_SECRET
            scope:
              - repo
```

---

### 4. Run Application

```bash
mvn spring-boot:run
```

---

## 🔑 Authentication Step (IMPORTANT)

Open browser:

```
http://localhost:8080/oauth2/authorization/github
```

* Login with GitHub
* Allow access

After login, session is created in browser.

---

## 📡 API Endpoints

### 🔹 1. Get Repositories

```
GET /api/github/repos
```

---

### 🔹 2. Create Issue

```
POST /api/github/issues
```

Body:

```json
{
  "owner": "your-username",
  "repo": "your-repo-name",
  "title": "Test Issue from API",
  "body": "Created using Spring Boot OAuth App"
}
```

---

### 🔹 3. List Issues

```
GET /api/github/issues?owner=your-username&repo=your-repo-name
```

---

## 🧪 Testing with Postman (IMPORTANT)

Since Spring Security uses **session-based authentication**, Postman must include session cookie.

### Step 1: Login via Browser

```
http://localhost:8080/oauth2/authorization/github
```

---

### Step 2: Copy Session Cookie

Open Browser DevTools → Application → Cookies

Copy:

```
JSESSIONID=XXXXXXXXXXXXX
```

---

### Step 3: Add in Postman Headers

```
Cookie: JSESSIONID=XXXXXXXXXXXXX
```

---

### Step 4: Send Request

Now API calls from Postman will work.

---

## ⚠️ Common Issues & Fixes

### 403 Forbidden (POST requests)

* Cause: CSRF protection
* Fix: CSRF disabled in SecurityConfig

---

### 401 Unauthorized

* Login not done
* Session cookie missing

---

### redirect_uri_mismatch

* Ensure callback URL matches exactly:

```
http://localhost:8080/login/oauth2/code/github
```

---

## 📌 Notes

* Do NOT commit client-secret to GitHub
* Replace credentials before running
* OAuth scope `repo` is required for creating issues

---

## 🎥 Demo Video

A demo video showing setup and API usage is included in the submission.
Watch here:    https://drive.google.com/file/d/1MS1zo5rg5j7axw7hRWMPLT5LC8qvGeFg/view?usp=sharing

---

## 🧠 Key Learnings

* OAuth2 authentication flow
* Secure API integration
* Handling external APIs
* Session vs stateless authentication
* Spring Security concepts
