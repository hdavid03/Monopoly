# Többszereplős Monopoly társasjáték specifikáció

A játék lényege, hogy telkeket és épületeket vásárolnak a játékosok, amelyeket eladni, illetve bérelni is lehet. Egy játékban maximum 4 fő vehet részt, akik közül mind az kiesik, aki fizetésképelenné válik, más szóval csődbe megy. A játékot az a játékos nyeri meg, aki monopóliumhoz jut.

### Játék mezők
- **Start mező:** Innen kezdődik a játék, vagyis kezdetben minden játékos figurája ezen a mezőn helyezkedik el.
- **Telek:** Ha egy játékos egy telek mezőre ér, amelyet még senki sem tulajdonol, akkor lehetősége van az adott telket megvásárolni a banktól. Ha a játékos nem él a vásárlási jogával, akkor a bank aukciót hirdet, s ekkor a legtöbb pénzt ajánló játékos birtokába jut a telek. A kezdő licitár tetszőleges értékű, és bármelyik játékos tehet árajánlatot, beleértve azt is, aki visszautasította a vásárlási jogát.  A telek friss tulajdonosa a banktól megkapja a telek birtoklevelét.
- **Pihenő hely**: Más nevén **Szabad parkoló** mező, ahol a játékos nem kap semmilyen jutalmat, de díjat sem kell fizetnie.
- **Börtön mező**: Ha erre a mezőre lép a játékos, attól még nem éri büntetés (_lásd következő pont_), ő ilyenkor csak látógatónak számít. A következő körben innen haladhat tovább a játékos a megszokott módon.
- **_Irány a börtön!_ mező**: Erre a mezőre érve a játékos büntetésben részesül, ami azt jelenti, hogy azonnal a _Börtön mezőre_ kell lépnie. A börtönhöz tartozó szabályok a **A játék szabályai** cím alatt szerepelnek.
- **Közmű mező**: Ha erre a mezőre ér a játékos, akkor lehetősége van megvásárolni az adott közműt, amely bevételi forrást jelent.
- **Vasúti társaság**: Hasonlóan a közmű mezőhöz, a mezőre lépett játékos megvásárolhatja az adott vasútvonalat, amely ugyan más szabály mentén, de ugyanúgy bevételi forrást jelent a tulajdonosnak, mint a közmű.
- **Szerencse/Meglepetés kártya mező**: Ha erre a mezőre lép a játékos, akkor egy _szerencse/meglepetés_ kártyát kell húznia, és a rajta szereplő utasítsnak eleget kell tennie.

### A játék szabályai
#### A játék kezdése
A játék elején minden játékos igazságosan egyforma pénzösszeget kap a Banktól. Első lépésként minden játékos dob egy-egy számot a két dobókockával és aki a legtöbbet dobja, az kezdi a játékot. A _Start_ mezőről indul a játék, ekkor a kockákkal újra dobni kell és a dobott számnak megfelelő mezőt kell előre lépni. Ezután a következő játékos következik, majd a következő és így tovább. Minden játékos, aki áthalad újra a _Start_ mezőn, az egy adott összegű pénzösszeget kap. Egy adott mezőn akár több bábu is állhat. Ha egy játékos a két kockával azonos számokat dob, akkor miután előre lépett, és a mezőhöz tartozó utasítást is végrehajtotta, akkor újra dobnia kell. Ha ebben az esetben is egyforma számok szerepelnek a kockán, akkor hasonlóan kell eljárnia. Viszont ha harmadik esetben is egyforma számokat dob, akkor azonnal börtönbe kerül. A játékosok játék közben telkeket, közművet és vasútvonalakat vásárolhatnak, ha az adott mezőre lépnek. A játék lényege, hogy ha egy játékos telek mezőre lép, akkor a tulajdonosának bérleti díjat kell fizetnie.

### A játék tartozékai
- 2 db dobókocka
- 4 db bábu
- 32 db ház
- 12 db ingatlan
- 2 csomag kártya
- Minden ingatlanhoz birtoklevél
- Papírpénz
- Játéktábla (telekhelyek, közművek, vasút társaságok, börtön és pihenőhely)

