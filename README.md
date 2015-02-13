# GameShopAdvance

GameShopAdvance is a POS (Point-of-sale) software system, designed specifically for a videogame store.

GameShopAdvance was specifically designed and developed as project work for Software Engineering course at University of L'Aquila by three students:

- Andrea Salini[@mrsalx]
- Lorenzo Di Giuseppe[@Loki88]
- Matteo Gentile[@darkwiz]

>The system is focused on the sale of video
>games, consoles, manuals, accessories, gadgets
>and their exchange, foreseeing exchange
>and/or recycling policies. The system relies on
>a distributed architecture, in this way, many
>different users can use part of application
>according to their needs.

### Version
1.0

The version you can get from here is fully working, but some requirements needs to be met!

### Technology constraints

GameShopAdvance needs open source software to work properly:

* [Java Runtime Environment] - Java SE Runtime Environment 7
* [db4o] - database for objects, Java Version
* [joda] - Joda-Time is the *de facto* standard date and time library for Java. (2.3 release and greater)

### How to run

<h5>On a Debian/Ubuntu based machine:</h5>

- Open your favorite Terminal and run these commands.
- Open your /etc/hosts as superuser with your favorite editor
- Comment out the following line
- `127.0.1.1  <your-username>` to `#127.0.1.1 <your-username>`
- Add ` <your-ip-address> <your-username> ` just below the commented out line

To run the Application server:

```
$ git clone [git-repo-url]
$ cd GameshopAdvance/build/Server
$ sh ./GSA_server.sh
```

To run the Customer client (assuming that the clone step has already been executed):

```
$ cd GameshopAdvance/build/Client\ Customer
$ sh ./GSA_client_customer.sh
```

<h5>On a Windows based machine:</h5>

- Open a Command window

To run the Application server:
```
C:\your-home-directory> cd C:\path-to-cloned-GameShopAdvance-directory\build\Server
C:\path-to-cloned-GameShopAdvance-directory\build\Server> GSA_server.bat
```
To run the Employee client
```
C:\your-home-directory> cd C:\path-to-cloned-GameShopAdvance-directory\Client Employee
C:\path-to-cloned-GameShopAdvance-directory\build\Client Employee> GSA_client.bat
```
<h4>How to test:</h4>
If you want to test the customer client, you have to insert products code and quantity to conclude a sale. Quantity is just a number, codes at this point of development are just strings but they generally follow some standard. These are the available products and them codes:

| Product Code  | Product Name | Price |
|---------------|--------------|-------|
| ab1           | Half Life 2  | 29.98 |
| ab2           | Enemy Territory: Quake Wars  | 19.99 |
| ab3           | Diablo 2  | 40.98 |
| ab4           | Super Mario Galaxy  | 40.98 |
| ab5           | GTA IV  | 70.98 |
| ab6           | Guitar Hero III  | 53.73 |
| ab7           | Minecraft  | 20.00 |
| ab8           | Il segreto di Monkey Island  | 43.50 |
| ab9           | NBA Live 2014  | 41.98 |
| ab10          | Pes 2014  | 30.98 |
| ab11          | Skyrim  | 40.98 |
| ab12          | South Park: il bastone della verità  | 50.98 |
| ab13          | Super Mario Bros U  | 35.98 |
| ab14          | Watch Dogs  | 60.98 |
| ab15          | Angry Birds: Star Wars  | 39.98 |
| ab16          | Call of Duty 4: Modern Warfare  | 30.98 |
| ab17          | Diablo 3  | 40.98 |
| ab18          | Doom 2  | 9.99 |
| ab19          | Metal Gear Solid 5  | 53.73 |
| cd1           | Xbox One  | 499.99 |
| cd2           | Joystick PS4  | 40.98 |
| cd3           | Cuffie Wi-Fi  | 80.98 |

NB. Prices are not realistic.

<h4>Warning:</h4>

When a Client is started for the first time must be configured it from the configuration menu in the window. You should insert the IP address of the machine where the application server is running.

GSA stores products in a data-base. Since some products prices or discounts expire in time, if you experiment problems with the application it's suggested to delete the db, that will be created again at runtime. To delete the data-base simply open the server folder and remove "GSA.db".

License
----

MIT

[@darkwiz]:https://github.com/mrsalx
[@mrsalx]:https://github.com/mrsalx
[@Loki88]:https://github.com/Loki88
[Java Runtime Environment]:http://www.oracle.com/technetwork/java/javase/downloads/jre7-downloads-1880261.html
[db4o]:http://supportservices.actian.com/versant/default.html
[joda]:https://github.com/Joda
[git-repo-url]:https://github.com/GameShopAdvance/GameShop-Advance.git
