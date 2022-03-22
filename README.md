# Többszereplős Monopoly társasjáték specifikáció

A játék lényege, hogy telkeket és épületeket vásárolnak a játékosok, amelyeket eladni, illetve bérelni is lehet. Egy játékban maximum 4 fő vehet részt, akik közül mind az kiesik, aki fizetésképelenné válik, más szóval csődbe megy. A játékot az a játékos nyeri meg, aki monopóliumhoz jut.

## Játék mezők
- **Start mező:** Innen kezdődik a játék, vagyis kezdetben minden játékos figurája ezen a mezőn helyezkedik el.
- **Telek:** Ha egy játékos egy telek mezőre ér, amelyet még senki sem tulajdonol, akkor lehetősége van az adott telket megvásárolni a banktól. Ha a játékos nem él a vásárlási jogával, akkor a bank aukciót hirdet, s ekkor a legtöbb pénzt ajánló játékos birtokába jut a telek. A kezdő licitár tetszőleges értékű, és bármelyik játékos tehet árajánlatot, beleértve azt is, aki visszautasította a vásárlási jogát.  A telek friss tulajdonosa a banktól megkapja a telek birtoklevelét.
- **Pihenő hely**: Más nevén **_Szabad parkoló_** mező, ahol a játékos nem kap semmilyen jutalmat, de díjat sem kell fizetnie.
- **Börtön mező**: Ha erre a mezőre lép a játékos, attól még nem éri büntetés (lásd _A börtönzés szabályai_ alcímet), ő ilyenkor csak látógatónak számít. A következő körben innen haladhat tovább a játékos a megszokott módon.
- **_Irány a börtön!_ mező**: Erre a mezőre érve a játékos büntetésben részesül, ami azt jelenti, hogy azonnal a _Börtön mezőre_ kell lépnie. A börtönhöz tartozó szabályok a **A játék szabályai** cím alatt szerepelnek.
- **Közmű mező**: Ha erre a mezőre ér a játékos, akkor lehetősége van megvásárolni az adott közműt, amely bevételi forrást jelent.
- **Vasúti társaság**: Hasonlóan a közmű mezőhöz, a mezőre lépett játékos megvásárolhatja az adott vasútvonalat, amely ugyan más szabály mentén, de ugyanúgy bevételi forrást jelent a tulajdonosnak, mint a közmű.
- **Jövedelem adó mező**: Ha erre a mezőre ér a játékos, akkor a mezőn szereplő pénzösszeget kell fizetnie a Bank részére.
- **Szerencse/Meglepetés kártya mező**: Ha erre a mezőre lép a játékos, akkor egy _szerencse/meglepetés_ kártyát kell húznia, és a rajta szereplő utasítsnak eleget kell tennie.

## A játék szabályai

### A játék kezdése
A játék elején minden játékos igazságosan egyforma pénzösszeget kap a Banktól. Első lépésként minden játékos dob egy-egy számot a két dobókockával és aki a legtöbbet dobja, az kezdi a játékot. A _Start_ mezőről indul a játék, ekkor a kockákkal újra dobni kell és a dobott számnak megfelelő mezőt kell előre lépni. Ezután a következő játékos következik, majd a következő és így tovább. Minden játékos, aki áthalad újra a _Start_ mezőn, az egy adott összegű pénzösszeget kap. Egy adott mezőn akár több bábu is állhat. Ha egy játékos a két kockával azonos számokat dob, akkor miután előre lépett, és a mezőhöz tartozó utasítást is végrehajtotta, akkor újra dobnia kell. Ha ebben az esetben is egyforma számok szerepelnek a kockán, akkor hasonlóan kell eljárnia. Viszont ha harmadik esetben is egyforma számokat dob, akkor azonnal börtönbe kerül. A játékosok játék közben telkeket, közművet és vasútvonalakat vásárolhatnak, ha az adott mezőre lépnek, miközben bérleti díjat szedhetnek be a tulajdonukban lévő telkek után.

### A házépítés szabályai
Ha a játékosok magasabb jövedelemre szeretnének szert tenni, akkor a birtokukban lévő telkekre ingatlanokat kell építeniük. A más játékosoktól beszedhető bérleti díj annál magasabb, minél több ház áll az adott telken. Minden telekhez tartozik egy birtoklevél, amely meghatározza a beszedhető alap bérleti díjat és azt, hogy mennyibe kerül az adott telken egy ház építése. Ezen felül minden telek meg van jelölve egy adott színnel, ami alapján 4 különböző csoportba sorolhatóak. A telkek ára előre meghatározott összeg, amelyet vásárlás esetén a Banknak kell megfizetni. Ha egy játékosnak sikerül megszerezni egy adott színcsoportba tartozó összes telket, akkor a játékos megveheti az első házat az egyik tetszőlegesen választott, adott színcsoportba tartozó telekre. A következő házak vásárlásánál arra kell figyelnie a játékosnak, hogy színcsoportonként arányosan épüljenek be a telkek. A legelső ház vásárlása után a következő házat csak az üres telkekre építheti meg, függetlenül attól, hogy az milyen színcsoportba tartozik. Miután a játékosnak már minden telkén áll egy ház, amelyek egy színcsoportba tartoznak, akkor kezdheti építeni az azonos színű telkekre a második házat, majd ugynanígy a harmadikat, aztán legvégül minden telken maximum 4 darab ház állhat.
> *Ha például egy játékosnak egy színcsoportba tartozó összes telkén áll 3 darab ház, és ezek közül egy házat eladni kényszerül, akkor addig nem építheti meg egyik azonos színű telkén sem a negyedik házat, amíg az emlíŧett telken csak 2 ház áll.*

Fontos szabály még, hogy jelzálog alatt álló telekre nem lehet építkezni, illetve ha Bank kevesebb házat tud eladni, mint amennyit a játékosok vásárolni szeretnének, akkor a házakra licitálni kell, és a legmagasabb árat kínáló játékosé lesz az ingatlan.
#### Szállodák
Ha egy játékosnak egy színcsoporthoz tartozó telkein a maximális 4 darab ház áll, akkor dönthet úgy, egy tetszőlegesen választott telekre épít egy szállodát, amellyel még magasabb bérleti díjra lesz jogosult, ezzel tovább növelve a jövedelmét. Egy telken csak egy szálloda állhat. A szálloda árát szintén a telekhez tartozó Biroklevél határozza meg.

### Jelzálog kölcsön
Minden játékos vehet fel kölcsönt, de kizárólag csak telkeket lehet jelzálogosítani. A Birtoklevélen szerepel a kölcsön összege, amit a Bank a jelzálogért cserébe fizet a játékosnak. A kölcsön ára 10% kamat, amelyet a kölcsön visszafizetésekor kell kifizetni. Ha egy játékos egy másik játékostól olyan telket vesz, amelyen jelzálog kölcsön van, akkor az új játékosnak kell visszafizetnie a kölcsönt a Banknak. Ha a vásárló játékos ekkor még nem tudja a teljes kölcsönt visszaadni a Banknak, akkor is meg kell adnia a 10% kamatot, majd amikor kifizeti a kölcsönt, akkor újabb 10%-ot kell fizetnie. Vagyis a vásárlás akkor előnyösebb, ha a jelzálog kölcsönt a játékos azonnal meg tudja adni a Banknak, így a 10% kamat összeget csak egyszer kell megfizetnie.

### A börtönzés szabályai
Egy játékos három különböző úton kerülhet börtönbe:
1. Ha a játékos _Irány a börtön!_ mezőre lép,
2. ha olyan meglepetés kártyát húz, amely arra utasítja, hogy vonuljon börtönbe,
3. illetve ha háromszor dob egymás után egyforma számokat a dobókockákkal.
> Ha a játékos börtönbe kerül, akkor a bábuját azonnal a _Börtön mezőre_ kell elhelyeznie, ráaduásul a börtönbe vezető úton, ha áthalad a _Start_ mezőn, akkor sem veheti fel az elvileg neki járó pénzjualmat.

A játékosnak három lehetősége adott, hogy kiszabduljon a börtönből:
1. Ha a börtönbe került játékos a következő alkalommal háromszor dob egymás után egyforma számokat a kockákkal. Ha sikerül neki, akkor a három dobás összegének megfelelő lépést tehet előre. Ezt a módszert három körön keresztül próbálhatja meg, ha egyszer sem sikerül, akkor a negyedik körben automatikusan kiszabadulhat.
2. Ha a játékos birtokában van egy _Kiszabadulhatsz a börtönből_ kártya, akkor akár azonnali felhasználásával börtönbe sem kell vonulnia.
3. Ezenfelül, ha a játékos a börtönbe vonulást követő körben kifizeti a bírságot, akkor is kiszabadulhat. 
Amíg egy játékos börtönben tartózkodik, a pályán nem haladhat előre, de vásárolhat és el is adhat telkeket, házakat, illetve a bérleti díjakat is beszedheti.

### Csőd
Az a játékos, aki több pénzzel tartozik, mint amennyit ki tud fizetni, az csődöt mond és kiesik a játékból. Ha egy játékos nem rendelkezik elegendő készpénzzel, hogy kifizesse tartozását a hitelezőjének és vannak házai/szállodái, akkor azokat a Birtoklevél által meghatározott összegért el kell adnia a Banknak, és ezt a pénzt oda kell adnia a hitelezőjének. Ha ez a pénz nem fedezi az adósságot, akkor a Bank automatikusan elkobozza az összes vagyonát, amire a játékban maradt játékosok licitálhatnak. A jelzálogosított telkekre vonatkozó szabály itt is érvényes. 

## A játék tartozékai
- 2 db dobókocka
- 4 db bábu
- 32 db ház
- 12 db szálloda 
- 2 csomag kártya (meglepetés és szerencse kártyák)
- Minden telekhez birtoklevél
- Játékpénz
- Játéktábla (telekhelyek, közművek, vasút társaságok, börtön és pihenőhely)

