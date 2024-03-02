# MovieGO

[![pl](https://img.shields.io/badge/lang-pl-red.svg)](README-PL.md)

As part of the student project, a mobile application has been created for reserving seats in the cinema. The application is designed for Android system users. The main goal of the project is to create a system intended for cinema customers. This allows users to browse scheduled screenings and make reservations.

![demo](./imgs/output_faster.gif)

Cinema Management Application:: https://github.com/EagleBlood/MovieGOAdmin
<br>
<br>

## Functionality
The application, upon initiation, retrieves information about scheduled screenings from the database through the Spring Boot API.<br>

The main view of the application serves as a calendar. In the top slider, dates are displayed, with the current day highlighted in red. Below, there are sliders with added items. Selecting an item from the slider leads to detailed movie information (point 6. Movie details).

<p align="center">
  <img src="https://github.com/EagleBlood/MovieGO/blob/main/imgs/1.png" alt="Ekran główny" width="270" height="585"/>
</p>
<br>

In the <i>Profile</i> view, user information is displayed, including: profile name, password, first name, last name, email, phone number, residential address, and date of birth.

<div align="center">
  <table>
    <tr>
      <td style="text-align: center;">
        <img src="https://github.com/EagleBlood/MovieGO/blob/main/imgs/2.1.png" alt="Profil urzytkownika1" width="270" height="585"/>
      </td>
      <td style="text-align: center;">
        <img src="https://github.com/EagleBlood/MovieGO/blob/main/imgs/2.2.png" alt="Profil urzytkownika2" width="270" height="585"/>
      </td>
    </tr>
  </table>
</div>
<br>

In the following view, the user's recent transactions are displayed. Each item shows: movie title, order number, date, time of the screening and the number of seats reserved. By clicking on an item, it expands, and the user can view more details about the transaction, including the reserved seats and the purchase cost.

<p align="center">
  <img src="https://github.com/EagleBlood/MovieGO/blob/main/imgs/3.png" alt="Ekran transakcji" width="270" height="585"/>
</p>
<br>

If an unauthenticated user navigates to the <i>Profile</i> view, <i>Tickets</i> view, or selects the <i>Login</i> option in the navigation, they will be directed to the login page. A registered user will be able to log in to their account. To do so, they will need to provide the email address they used during registration and their password.

After selecting <i>Login</i>, the user will be redirected to the <i>Registration</i> view.

<p align="center">
  <img src="https://github.com/EagleBlood/MovieGO/blob/main/imgs/4.png" alt="Ekran rejestracji" width="270" height="585"/>
</p>
<br>

Below, we present the model of the <i>Form</i> View that allows users to send a message to the system administrator. Please note that the functionality has not been implemented yet.

<p align="center">
  <img src="https://github.com/EagleBlood/MovieGO/blob/main/imgs/5.png" alt="Ekran formularz" width="270" height="585"/>
</p>
<br>

In the movie details view, we display basic information about the film, including the title, genre, duration, and description.

<p align="center">
  <img src="https://github.com/EagleBlood/MovieGO/blob/main/imgs/6.png" alt="Szczegóły filmu" width="270" height="585"/>
</p>
<br>

After selecting the back arrow, the user will be redirected to the main page view. If the user is logged in, selecting <i>Reserve a Seat</i> will lead them to the <i>Movie Hall</i> view.<br>

In the following view, the logged-in user can make seat selections in the cinema hall. Pressing on a square will select the seat, and pressing it again will deselect it. If a seat is filled with a gray color, it means that it is already unavailable.

<div align="center">
  <table>
    <tr>
      <td style="text-align: center;">
        <img src="https://github.com/EagleBlood/MovieGO/blob/main/imgs/7.1.png" alt="Hala kina1" width="270" height="585"/>
      </td>
      <td style="text-align: center;">
        <img src="https://github.com/EagleBlood/MovieGO/blob/main/imgs/7.2.png" alt="Hala kina2" width="270" height="585"/>
      </td>
    </tr>
  </table>
</div>
<br>

For the selected seats, their positions will be displayed in the list. On the left side, information about the seat will be shown (e.g., 1:3 - row 1, seat 3), and on the right, the ticket price. At the bottom, the final price will be displayed, along with a button to complete the transaction. Pressing the button will save the transaction and return to the previous view.<br>

In this view, the user can register by providing the following information: username, email address, and password (entered twice for confirmation). After selecting "Sign Up," the system will save the data and redirect the user to the login view.<br>

<p align="center">
  <img src="https://github.com/EagleBlood/MovieGO/blob/main/imgs/8.png" alt="Ekran rejestracji" width="270" height="585"/>
</p>
<br>

The application menu allows users to navigate to the following views: home (point 1), login (point 4), and form (point 5). The functionality for "Check for updates" has not been implemented. For logged-in users, the "Login" option in the menu is replaced with "Log Out."
<br>
<br>


## Installation
- Clone the project repository from GitHub to a local directory on your computer.
- Open the project in the Android Studio IDE.
- To load images, a database and an internet connection are required.
<br>
<br>

## Tools and libraries
* Android Studio Dolphin (v. 2021.3.1 Patch 1)
* Spring Boot (v. 3.0.6)
* Gson (v. 2.8.9)
* androidx.legacy:legacy-support-v4:1.0.0
* com.google.android.material:material:1.9.0
* androidx.core:core-ktx:1.6.0
* com.squareup.retrofit2:retrofit:2.9.0
* com.squareup.retrofit2:converter-gson:2.9.0
* androidx.appcompat:appcompat:1.6.1
* androidx.constraintlayout:constraintlayout:2.1.4
* androidx.lifecycle:lifecycle-livedata-ktx:2.6.1
* androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1
* androidx.navigation:navigation-fragment:2.5.3
* androidx.navigation:navigation-ui:2.5.3
* androidx.navigation:navigation-fragment-ktx:2.5.3
* androidx.databinding:databinding-runtime:8.0.0
* junit:junit:4.13.2
* androidx.test.ext:junit:1.1.5
* androidx.test.espresso:espresso-core:3.5.1
