# Quizlet
## Tech Stack & Tools
- Spring Boot
- JDBC Template
- MySQL
- JSTL
- Authentication using OncePerRequestFilter

## Description
Quizlet is an application where the users can take quizzes from different categories. 
5 Questions are randomly assigned to each quiz, and the user have to pass more than three 
of them to pass. 

Overall:
  - each page is implemented with Authentication using OncePerRequestFilter
  - authorization: user, admin
  - a global navigator bar

User Section:
- Login Page:
    - default page of the application
    - if not logged in, will be redirected to this page

- Homepage:
    - display all the quiz categories available to choose from
    - display all the quizzes the user has taken, along with the quiz details

- Quiz Screen:
  - 5 randomly chosen questions
  - a submit button to submit the quiz

- Quiz Result:
  - display the start/end time
  - whether the user has passed the quiz
  - the correct answer to each question
    and the user selected choice
  - an option to take another quiz

- Feedback:
  - users can submit an anonymous feedback with rating feature
- Contact Us:
  - users can send a message to the Admin

Admin Section:
- can see an overview of all quiz results
  - quiz details are available after clicking on the specific quiz
  - shows the correct answers and user selected answers
  - quiz results are sorted by taken date
- CRUD operations to users, categories, questions and their answers
- suspend/activate a user
- view all feedbacks and contact messages