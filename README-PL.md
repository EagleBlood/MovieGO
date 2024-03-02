# MovieGO
W ramach projektu studenckiego została stworzona aplikacja mobilna do rezerwowania miejsc w kinie. Aplikacja przeznaczona dla użytkowników systemu Android. Głównym celem projektu jest stworzenie systemu przeznaczonego dla klientów kina. Dzięki temu użytkownicy mogą przeglądać zaplanowane seanse oraz dokonywać rezerwacji.

![demo](./imgs/output_faster.gif)

Aplikacja do zarządzania kinem: https://github.com/EagleBlood/MovieGOAdmin
<br>
<br>

## Funkcjonalność
Aplikacja przy inicjacji pobiera poprzez Spring Boot API informacje na temat zaplanowanych seansów z bazy danych.<br>

Główny widok aplikacji pełni rolę kalendarza. W górnym sliderze dostępne są daty, aktualny dzień jest wyróżniony kolorem czerwonym.
Poniżej znajdują się slidery z dodanymi pozycjami. Wybranie pozycji ze slidera prowadzi do szczegółowego opisu filmu (punkt 6. Movie details). 

<p align="center">
  <img src="https://github.com/EagleBlood/MovieGO/blob/main/imgs/1.png" alt="Ekran główny" width="270" height="585"/>
</p>
<br>

W widoku <i>Profil</i> wyświetlane są dane o użytkowniku. Są to: nazwa profilu, hasło, imię, nazwisko, email, numer telefonu, adres zamieszkania oraz datę urodzenia. 

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

W poniższym widoku wyświetlane są ostatnie transakcje użytkownika. W elemencie wyświetlane są: nazwa filmu, numer zamówienia, data i godzina seansu oraz ilość miejsc. Po naciśnięciu na element następuje rozwinięcie go i użytkownik może zobaczyć więcej informacji o dokonanej transakcji - zarezerwowane miejsca oraz koszt zakupu.

<p align="center">
  <img src="https://github.com/EagleBlood/MovieGO/blob/main/imgs/3.png" alt="Ekran transakcji" width="270" height="585"/>
</p>
<br>

Jeśli niezalogowany użytkownik, który przechodzi do widoku profilu, widoku biletów lub wybiera pozycję logowanie w nawigacji jest kierowany do strony logowania. Zarejestrowany użytkownik będzie mógł zalogować się na swoje konto. W tym celu będzie musiał podać adres mailowy na który został podany przy rejestracji oraz hasło. 

Po wybraniu <i>Zaloguj się</i> następuje przeniesie do widoku <i>Rejestracji</i>

<p align="center">
  <img src="https://github.com/EagleBlood/MovieGO/blob/main/imgs/4.png" alt="Ekran rejestracji" width="270" height="585"/>
</p>
<br>

Poniżej przedstawiamy model widoku <i>Formularza</i>, za pomocą którego użytkownik będzie mógł wysłać wiadomość do administratora systemu. Funkcjonalność,nie została zaimplementowana.

<p align="center">
  <img src="https://github.com/EagleBlood/MovieGO/blob/main/imgs/5.png" alt="Ekran formularz" width="270" height="585"/>
</p>
<br>

W widoku szczegółów filmu wyświetlamy podstawowe informacje o filmie: tytuł, typ filmy, czas trwania oraz opis.

<p align="center">
  <img src="https://github.com/EagleBlood/MovieGO/blob/main/imgs/6.png" alt="Szczegóły filmu" width="270" height="585"/>
</p>
<br>

Po wybraniu strzałki wstecz następuje przeniesienie do widoku strony głównej. Jeśli użytkownik jest zalogowany, po wybraniu <i>Zarezerwuj miejsce</i> następuje przejście do widoku Movie hall.

W poniższym widoku zalogowany użytkownik może dokonać wyboru miejsc w sali kinowej. Po naciśnięciu kwadratu następuje wybranie miejsca. Ponowne naciśnięcie odznacza wybór. Jeśli miejsce będzie wypełnione kolorem szarym tzn. że jest już niedostępne.<br>

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


Dla wybranych miejsc wyświetlą się pozycje na liscie. Po lewej jest wyświetlana informacja o miejscu (1:3 - rząd 1 miejsce 3), a po prawej o cenie biletu. Na samym dole wyświetlana jest cena końcowa oraz przycisk zakończenia transakcji. Po jego naciśnięciu następuje zapisanie transakcji oraz przejście do poprzedniego widoku.

W tym widoku użytkownik może się rejestrować. W tym celu system przyjmuje następujące informacje: login, adres mailowy oraz hasło (pobierane dwa razy w celu potwierdzenia). Po wybraniu sign up nastąpuje zapisanie danych oraz przeniesie do widoku logowania.<br>

<p align="center">
  <img src="https://github.com/EagleBlood/MovieGO/blob/main/imgs/8.png" alt="Ekran rejestracji" width="270" height="585"/>
</p>
<br>

Menu aplikacji umożliwia przejście do następujących widoków: home (pkt 1), login (pkt 4), form (pkt 5). Funkcjonalność dla Check for updates nie została zaimplementowana. Dla zalogowanego użytkownika w pozycji login pojawia się opcja Wyloguj się. 
<br>
<br>


## Instalacja
- Sklonuj repozytorium projektu z GitHuba do lokalnego katalogu na swoim komputerze.
- Uruchom program w środowkisku Android Studio IDE
- Żeby załatować zdjęcia potrzebna jest baza danych oraz połączenie z internetem*
<br>
<br>

## Narzędzia oraz biblioteki:
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
