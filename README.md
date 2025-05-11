**Local Setup Instructions**
**Prerequisites**
- Java 17+
- Node.js 16+
- PostgreSQL (locally running with postgres / postgres credentials)

Code is split across 2 folders namely frontend and backend

Backend
cd backend
./gradlew bootRun
Make sure PostgreSQL has a DB named school_vaccine.
Access the backend swagger at: http://localhost:8080/swagger-ui/index.html#/

Frontend

cd frontend
npm install
npm run dev
Access the frontend at: http://localhost:5173

Login via google account to access the dasboard and perform actions
