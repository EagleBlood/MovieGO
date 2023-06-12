# MovieGO
W ramach projektu studenckiego została stworzona aplikacja mobilna do rezerwowania miejsc w kinie. Aplikacja przeznaczona dla użytkowników systemu Android.
Głównym celem projektu jest stworzenie systemu przeznaczonego dla klientów kina. Dzięki temu użytkownicy mogą przeglądać zaplanowane seanse oraz dokonywać rezerwacji.

## Funkcjonalność

Główny widok aplikacji pełni rolę kalendarza. W górnym sliderze dostępne są daty, aktualny dzień jest wyróżniony kolorem czerwonym.
Poniżej znajdują się slidery z dodanymi pozycjami. Wybranie pozycji ze slidera prowadzi do szczegółowego opisu filmu (punkt 6. Movie details). 



W widoku <i>Profil</i> wyświetlane są dane o użytkowniku. Są to: nazwa profilu, hasło, imię, nazwisko, email, numer telefonu, adres zamieszkania oraz datę urodzenia. 



W poniższym widoku wyświetlane są ostatnie transakcje użytkownika. W elemencie wyświetlane są: nazwa filmu, numer zamówienia, data i godzina seansu oraz ilość miejsc. Po naciśnięciu na element następuje rozwinięcie go i użytkownik może zobaczyć więcej informacji o dokonanej transakcji - zarezerwowane miejsca oraz koszt zakupu.



Jeśli niezalogowany użytkownik, który przechodzi do widoku profilu, widoku biletów lub wybiera pozycję logowanie w nawigacji jest kierowany do strony logowania. Zarejestrowany użytkownik będzie mógł zalogować się na swoje konto. W tym celu będzie musiał podać adres mailowy na który został podany przy rejestracji oraz hasło. 

Po wybraniu <i>Zaloguj się</i> następuje przeniesie do widoku <i>Rejestracji</i>



Poniżej przedstawiamy model widoku "Formularza", za pomocą którego użytkownik będzie mógł wysłać wiadomość do administratora systemu.Funkcjonalność,nie została zaimplementowana.



W widoku szczegółów filmu wyświetlamy podstawowe informacje o filmie: tytuł, typ filmy, czas trwania oraz opis.

Po wybraniu strzałki wstecz następuje przeniesienie do widoku strony głównej. Jeśli użytkownik jest zalogowany, po wybraniu <i>Zarezerwuj miejsce</i> następuje przejście do widoku Movie hall.


W ponizszym widoku zalogowany użytkownik może dokonać wyboru miejsc w sali kinowej. Po naciśnięciu kwadratu następuje wybranie miejsca. Ponowne naciśnięcie odznacza wybór. Jeśli miejsce będzie wypełnione kolorem szarym tzn. że jest już niedostępne.

Dla wybranych miejsc wyświetlą się pozycje na liscie. Po lewej jest wyświetlana informacja o miejscu (1:3 - rząd 1 miejsce 3), a po prawej o cenie biletu.

Na samym dole wyświetlana jest cena końcowa oraz przycisk zakończenia transakcji. Po jego naciśnięciu następuje zapisanie transakcji oraz przejście do poprzedniego widoku.



W tym widoku użytkownik może się rejestrować. W tym celu system przyjmuje następujące informacje: login, adres mailowy oraz hasło (pobierane dwa razy w celu potwierdzenia). Po wybraniu sign up nastąpuje zapisanie danych oraz przeniesie do widoku logowania.



Menu aplikacji umożliwia przejście do następujących widoków: home (pkt 1), login (pkt 4), form (pkt 5). Funkcjonalność dla Check for updates nie została zaimplementowana. Dla zalogowanego użytkownika w pozycji login pojawia się opcja Wyloguj się. 

