# kafray

recive json-like data, check and send it to kafka.

It's also a demo of spray and sbt-native-packager.

#### Usage:

```
sbt run
```
Server listening on port `8080` by default,
edit `src/main/resources/application.conf` for another one.

### RPM Plugin
require `rpmbuild`.

For redhat: `yum install rpm`, or 
ubuntu: `sudo apt-get install rpm`.

1. build rpm

    ```
    sbt rpm:packageBin
    ```
    when succeed, a rpm package will be created in `target/rpm/RPMS/noarch/`.

2. install rpm

    ```
    rpm -ivh target/rpm/RPMS/noarch/kafray-1.0-1.noarch.rpm
    ```
    app location: `/usr/share/kafray`,
    app config directory: `/usr/share/kafray/conf`, 
    service config file location: `/etc/default/kafray`

3. start/stop app

    ```
    service kafray start/stop/status/restart
    ```
    if seeing `"No java installations was detected."` in log file,
    add `export JAVA_HOME=/path/of/java` to service config file.

4. costomize application

    enter app conf dir, edit `application.conf` and restart service.
