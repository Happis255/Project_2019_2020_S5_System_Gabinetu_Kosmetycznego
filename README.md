# Aplikacja weebowa wspomagająca pracę gabinetu kosmetycznego

System wspomagający pracę gabinetu kosmetycznego składa się z bazy danych odpowiedzialnej za przechowywanie wszystkich niezbędnych danych do funkcjonowania firmy kosmetycznej, na przykład: dane osobowe pracowników, spis raportów i aktualności zamieszczonych w systemie czy spis wszystkich wizyt wykonanych w gabinecie. Odpowiedzialna jest również za przechowywanie wszystkich wiadomości i powiadomień generowanych przez system. Ponadto baza danych zawiera również dane niezbędne do funkcjonowania aplikacji klienckiej hostowanej na serwerze webowym, która umożliwia rezerwowanie wizyt przez klientów, przedstawianie zakresu usług gabinetu jak i oferty sprzedażowej czy powiadamianie klienta o nowej promocji.

W celu zabezpieczenia aplikacji przed niepowołanym dostępem do danych gabinetu oraz przed funkcjonalnościami niedostępnymi dla danych użytkowników aplikacja posiada system logowania, gdzie użytkownik musi podać login oraz hasło w celu skorzystania z aplikacji. Poszczególni klienci posiadają możliwość rejestracji konta w systemie, natomiast konto pracownika może zostać założone jedynie przez administratora systemu. 

Wykorzystane technologie
1)	Java, JavaServer oraz Javax.Mail
2)	MySQL
3)	HTML oraz CSS
4)	Bootstrap Framework

### Struktura folderów

* src/myPagebasic/Objects - folder przechowywujący podstawowe klasy zawierające metody wykorzystywanych obiektów, np. załadowywanie z bazy danych informacji o aktualnościach.
* src/myPagebasic/data/dataBase - folder przechowywujący podstawowe klasy reprezentujące obiekty aplikacji
* src/myPagebasic/dataSourceDB - folder przechowywujący klasy odpowiedzialne za komunikacje z bazą danych
* src/myPagebasic/exceptions - folder przechowywujący wykorzystywane wyjątki
* src/myPagebasic/UniTests - folder przechowywujący klasy wykorzystywane przy testach jednostkowych (Przykładowy test na Userze oraz Wizytcie)
* src/myPagebasic/assets - folder przechowwyjący assety wykorzystywane w aplikacji (grafiki typu loga, zdjęcia) oraz plik stylu CSS
* src/myPagebasic/P_Administrator - folder przechowywujący pliki JSP wykorzystywane przez Administratora systemu
* src/myPagebasic/P_Klient - folde przechowywujący pliki JSP wykorzystywane przez Klienta systemu
* src/myPagebasic/P_Pracownik - folderz przechowywujący pliki JSP wykorzystywane przez Pracownika systemu
* src/myPagebasic/P_User - folder przechowywujący pliki JSP wykorzystywane przez osobę niezalogowaną w systemie
* src/myPagebasic/ - przechowuje stronę główną index.jsp, nagłówki, stopki oraz strony komunikatów dla osoby niezalogowanej.

### Funkcjonalności aplikacji

1) Logowanie i wylogowanie użytkowników – klient / administrator / pracownik.
2) Tworzenie kont pracowników, ich edycja oraz zarządzanie uprawnieniami (systemowymi jaki usługowymi) przez administratora.
3) Dodawanie przez administratora terminu szkoleń i kongresów kosmetycznych, informacji o ich zakresie, wyświetlanie ich w systemie oraz możliwość zapisu przez pracownika.
4) Dodawanie przez administratora bądź zalogowanego w gabinecie pracownika klienta wraz z zamówioną usługą do grafiku (kalendarza), możliwość jego edycji i podglądu.
5) Tworzenie „Kart Informacyjnych” zawierające wskazania i przeciwskazania do zabiegów oraz alergie i nietolerancje organizmu klienta możliwych do edytowania przez klienta oraz „Kart Zabiegowych” informujące o wykonywanych zabiegach u danego klienta.
6) Możliwość prowadzenia książeczki zdrowia pracowników.
7) Dodawanie zatwierdzonych osiągnięć – ukończone szkolenia oraz otrzymane certyfikaty – przez administratora do profilów pracowników oraz ich prezentacja.
8) Dodawanie sprawozdania zawierającego przebieg ostatnio wykonanego przeglądu technicznego (wewnętrzny), serwisowego (zewnętrzny) oraz sporale kontrolne (medyczne), ich daty przeprowadzenia oraz możliwość ustawienia przypomnienia.
9) Możliwość dodawania wybranych sprzętów użytkowych, informacji o nich oraz ich wyświetlanie.
10) Zarządzanie odpadami – utylizacja – generowanie sprawozdania odbioru zarejestrowanych odpadów (data oraz ilość), ustawienie okresowego przypomnienia 
o zbliżającym się terminie odbioru odpadów oraz generowanie rocznego bilansu utylizacji.
11) Zarządzanie stanem magazynowym materiałów kosmetycznych - możliwość zgłoszenia braków i propozycji zwiększające ofertę o nowości gabinetu przez pracownika oraz stworzenie listy zakupowej przez administratora na ich podstawie.
12) Rejestrowanie wizyt kontrolnych, takich jak sanepid, ZUS czy państwowa inspekcja pracy.
13) Możliwość generowania wykresu zawierającego ilość wykonanych poszczególnych usług w danym miesiącu.
14) Dodawanie przez administratora bądź wyznaczonego pracownika informacji 
o aktualnych ofertach, pakietach sezonowych, okolicznościowych, świątecznych  i konkursach oraz ich udostępnienie klientowi.
15) Prowadzenie programu motywacyjnego dla pracownika – nadawanie bonusów okolicznościowych i premii.
16) Możliwość prowadzenia spisu wykonanych usług, kto ich wykonał oraz kwota otrzymanej zapłaty.
17) Możliwość podglądu przez pracownika w swoim profilu informacji o wysokości nadchodzącej wypłaty.
18) Ustalanie z miesięcznym wyprzedzeniem grafiku oraz możliwość zgłaszania prośby o jego edycję przez pracownika.
19) Zgłaszanie prośby o przyznanie urlopu bądź poinformowanie o otrzymaniu zwolnienia lekarskiego przez pracownika administratorowi.
20) Możliwość wprowadzenia zakresu zadań gospodarczych poszczególnym pracownikom.
21) Dodawanie w systemie CV potencjalnego pracownika (w przypadku gdy poszukiwany jest pracownik) oraz przesłania go na mail administratora.
22) Możliwość konsultacji online poprzez chat online pomiędzy klientem a pracownikiem.
23) Możliwość przeglądania oferty usług udostępnianych przez gabinet oraz wybór wzorów/kolorów/typu makijażu przez klienta.
24) Możliwość wstępnej rezerwacji terminu wykonania wybranej usługi u wybranego pracownika.
25) Możliwość składania formularza reklamacyjnego.
26) Złożenie zamówienia przez klienta dotyczącego kupna produktu bądź usługi znajdującego się w ofercie gabinetu oraz opłacenia go przelewem bankowym.
27) Usługa lojalnościowa – klient otrzymuje status stałego klienta po skorzystaniu z określonej ilości usług zatwierdzonych przez pracownika. Takiemu klientowi przysługują bonusy określane przez właściciela gabinetu.
28) Automatyczne wysyłanie przypomnienia klientowi o wizycie (mail).
29) Informowanie klienta o procedurze przygotowania się do zabiegu.
30) Informowanie klienta o celu przetwarzania ich danych osobowych (RODO), przez kogo mogą być wyświetlane i kto nimi zarządza.

### Dokumentacja związana z projektem:
* <a href="https://github.com/Happis255/Project_2019_2020_S5_System_Gabinetu_Kosmetycznego/tree/master/Dokumentacja/In%C5%BCynieria%20Programowa">Inżynieria Programowa</a>
* <a href="https://github.com/Happis255/Project_2019_2020_S5_System_Gabinetu_Kosmetycznego/tree/master/Dokumentacja/Programowanie%20w%20j%C4%99zyku%20Java">Programowanie w języku Java</a>
* <a href="https://github.com/Happis255/Project_2019_2020_S5_System_Gabinetu_Kosmetycznego/tree/master/Dokumentacja/Technologie%20Obiektowe">Technologie Obiektowe</a>
* <a href="https://github.com/Happis255/Project_2019_2020_S5_System_Gabinetu_Kosmetycznego/tree/master/Dokumentacja/Systemy%20Baz%20Danych">Systemy Baz Danych</a>

### Podgląd aplikacji
* <a href="https://github.com/Happis255/Project_2019_2020_S5_System_Gabinetu_Kosmetycznego/blob/master/Dokumentacja/Systemy%20Baz%20Danych/s_sbd_pawlikowska_wasik_piskorz_dokumentacjaUzytkowa.pdf">Dokumentacja użytkowa</a>
