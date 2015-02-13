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
<h4>Warning:</h4>

When a Client is started for the first time must be configured it from the configuration menu in the window. You should insert the IP address of the machine where the application server is running.

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
