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

On a Debian/Ubuntu based machine:
- Open a command terminal
- Open your /etc/hosts as superuser with your favorite editor
- Comment out the following line
- `127.0.1.1  <your-username>` to `#127.0.1.1 <your-username>`
- Add ` <your-ip-address> <your-username> ` just below the commented out line

To run the Application server:

```sh
$ git clone [git-repo-url]
$ cd GameshopAdvance/build/Server
$ sh ./GSA_server.sh
```

To run the Customer client (assuming that the clone step has already been executed):

```sh
$ cd GameshopAdvance/build/Client\ Customer
$ sh ./GSA_client_customer.sh
```


License
----

MIT

[darkwiz]:https://github.com/mrsalx
[mrsalx]:https://github.com/mrsalx
[Loki88]:https://github.com/Loki88
[Java Runtime Environment]:http://www.oracle.com/technetwork/java/javase/downloads/jre7-downloads-1880261.html
[db4o]:ftp://ftp.versant.com/po/db4o/production/db4o-8.0.276.16149-java.zip
[joda]:https://github.com/Joda
[git-repo-url]:https://github.com/GameShopAdvance/GameShop-Advance.git
