# MovieGO
W ramach projektu studenckiego została stworzona aplikacja mobilna do rezerwowania miejsc w kinie. Aplikacja przeznaczona dla użytkowników systemu Android.
Głównym celem projektu jest stworzenie systemu przeznaczonego dla klientów kina. Dzięki temu użytkownicy mogą przeglądać zaplanowane seanse oraz dokonywać rezerwacji.

![demo](./imgs/output_faster.gif)
<br>
<br>

## Funkcjonalność
Główny widok aplikacji pełni rolę kalendarza. W górnym sliderze dostępne są daty, aktualny dzień jest wyróżniony kolorem czerwonym.
Poniżej znajdują się slidery z dodanymi pozycjami. Wybranie pozycji ze slidera prowadzi do szczegółowego opisu filmu (punkt 6. Movie details). 

<p align="center">
  <img src="https://cdn.discordapp.com/attachments/1078655641259888680/1117822539989782578/1.PNG" alt="Ekran główny" width="270" height="585"/>
</p>
<br>

W widoku <i>Profil</i> wyświetlane są dane o użytkowniku. Są to: nazwa profilu, hasło, imię, nazwisko, email, numer telefonu, adres zamieszkania oraz datę urodzenia. 

<div align="center">
  <table>
    <tr>
      <td style="text-align: center;">
        <img src="https://cdn.discordapp.com/attachments/1078655641259888680/1117822621749366864/8.1.png" alt="Profil urzytkownika1" width="270" height="585"/>
      </td>
      <td style="text-align: center;">
        <img src="https://cdn.discordapp.com/attachments/1078655641259888680/1117822631287205948/8.2.png" alt="Profil urzytkownika2" width="270" height="585"/>
      </td>
    </tr>
  </table>
</div>
<br>

W poniższym widoku wyświetlane są ostatnie transakcje użytkownika. W elemencie wyświetlane są: nazwa filmu, numer zamówienia, data i godzina seansu oraz ilość miejsc. Po naciśnięciu na element następuje rozwinięcie go i użytkownik może zobaczyć więcej informacji o dokonanej transakcji - zarezerwowane miejsca oraz koszt zakupu.

<p align="center">
  <img src="https://cdn.discordapp.com/attachments/1078655641259888680/1117822609707520030/7.png" alt="Ekran transakcji" width="270" height="585"/>
</p>
<br>

Jeśli niezalogowany użytkownik, który przechodzi do widoku profilu, widoku biletów lub wybiera pozycję logowanie w nawigacji jest kierowany do strony logowania. Zarejestrowany użytkownik będzie mógł zalogować się na swoje konto. W tym celu będzie musiał podać adres mailowy na który został podany przy rejestracji oraz hasło. 

Po wybraniu <i>Zaloguj się</i> następuje przeniesie do widoku <i>Rejestracji</i>

<p align="center">
  <img src="https://cdn.discordapp.com/attachments/1078655641259888680/1117822562236387468/3.png" alt="Ekran rejestracji" width="270" height="585"/>
</p>
<br>

Poniżej przedstawiamy model widoku "Formularza", za pomocą którego użytkownik będzie mógł wysłać wiadomość do administratora systemu. Funkcjonalność,nie została zaimplementowana.

<p align="center">
  <img src="https://cdn.discordapp.com/attachments/1078655641259888680/1117822640728588379/9.png" alt="Ekran formularz" width="270" height="585"/>
</p>
<br>

W widoku szczegółów filmu wyświetlamy podstawowe informacje o filmie: tytuł, typ filmy, czas trwania oraz opis.

<p align="center">
  <img src="https://cdn.discordapp.com/attachments/1078655641259888680/1117822575037386893/5.png" alt="Szczegóły filmu" width="270" height="585"/>
</p>
<br>

Po wybraniu strzałki wstecz następuje przeniesienie do widoku strony głównej. Jeśli użytkownik jest zalogowany, po wybraniu <i>Zarezerwuj miejsce</i> następuje przejście do widoku Movie hall.


W ponizszym widoku zalogowany użytkownik może dokonać wyboru miejsc w sali kinowej. Po naciśnięciu kwadratu następuje wybranie miejsca. Ponowne naciśnięcie odznacza wybór. Jeśli miejsce będzie wypełnione kolorem szarym tzn. że jest już niedostępne.


<div align="center">
  <table>
    <tr>
      <td style="text-align: center;">
        <img src="https://cdn.discordapp.com/attachments/1078655641259888680/1117822587003744306/6.1.png" alt="Hala kina1" width="270" height="585"/>
      </td>
      <td style="text-align: center;">
        <img src="https://cdn.discordapp.com/attachments/1078655641259888680/1117822599452426270/6.2.png" alt="Hala kina2" width="270" height="585"/>
      </td>
    </tr>
  </table>
</div>
<br>


Dla wybranych miejsc wyświetlą się pozycje na liscie. Po lewej jest wyświetlana informacja o miejscu (1:3 - rząd 1 miejsce 3), a po prawej o cenie biletu. Na samym dole wyświetlana jest cena końcowa oraz przycisk zakończenia transakcji. Po jego naciśnięciu następuje zapisanie transakcji oraz przejście do poprzedniego widoku.



W tym widoku użytkownik może się rejestrować. W tym celu system przyjmuje następujące informacje: login, adres mailowy oraz hasło (pobierane dwa razy w celu potwierdzenia). Po wybraniu sign up nastąpuje zapisanie danych oraz przeniesie do widoku logowania.

<p align="center">
  <img src="https://cdn.discordapp.com/attachments/1078655641259888680/1117822562236387468/3.png" alt="Ekran rejestracji" width="270" height="585"/>
</p>
<br>

Menu aplikacji umożliwia przejście do następujących widoków: home (pkt 1), login (pkt 4), form (pkt 5). Funkcjonalność dla Check for updates nie została zaimplementowana. Dla zalogowanego użytkownika w pozycji login pojawia się opcja Wyloguj się. 
<br>
<br>


## Instalacja
- Sklonuj repozytorium projektu z GitHuba do lokalnego katalogu na swoim komputerze.
- Uruchom program w środowkisku Android Studio IDE
<br>
<br>

## Narzędzia oraz biblioteki:
* Android Studio Dolphin (v. 2021.3.1 Patch 1)
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
