# Remote Access

===========================

## Contents

* IP address

How to find your Raspbeery Pi's IP address in order to connect to it.

* Access over Internet

Remote access to the Pi over the Internet using Weaved.

* VNC

Remote access to the Pi's graphical interface, viewed in a window on another computer.

* SSH

Access the command line of the Pi from another computer.

* SFTP

Copy files between your Pi and another computer using SFTP (Secure FTP).

* SCP

Copy files between your Pi and another computer using SCP (Secure Copy).

* SSHFC

Copy files between your Pi and another computer using SSHFC (SSH Filesystem).

* rsync

Synchronise folders between the Pi and another computer using rsync over SSH.

* FTP

Copy files between your Pi and another computer using FTP.

* Web Server

Set up a website or a web page to display some information about the Pi, using a web brower on another machine, on the network, or on the internet.


## IP Address

===========================

Any device connected to a Local Area Network is assigned an IP address.

In order ot connect to your Raspberry Pi from another machine using SSH or VNC, you need to know the Pi's IP address.

This is easy if you have a display connected, and there are a number of methods for finding it remotely from another machine on the network.

### Using the pi with a display

If you boot to the command line instead of the desktop, your IP address should be shown in the last few messages before the login prompt.

Using the terminal (boot the command line or open a Terminal window from the desktop), simple type **hostname -I** which will reveal your Pi's IP address.

### Using the pi headless(without a display)

It is possible to find the IP address of your Pi without connecting to a screen using one of the following method:

### Router devices list

In a web browser navigate to your router's IP address e.g. **http://192.168.1.1**, which is usually printed on a label on your router; this will take you to a control panel.

Then log in using your credentials, which is usually also printed on the router or sent to you in the accompanying paperwork.

Browse to the list of connected devices or similar (all routers are different), and you should see some devices you recognise.

Some devices are detected as PCs, tablets, phones, printers, etc.

so you should recognise some and rule them out to figure out which is you Raspberry Pi.

Also note the connection type; if your Pi is connected with a wire there should be fewer devices to choose from.

### nmap command

The **nmap** command (Network Mapper) is a free and open-source tool for network discovery, available for Linux, Mac OS, and Windows.

* To install on Linux, install the **nmap** package e.g. **apt-get install nmap**.
* To install on Mac OS or Windows, see the .[nmap.org.download page](https://nmap.org/download.html).

To use **nmap** to scan the devices on your network, you need to know the subnet you are connected to First find you own IP address, in other works the one of the computer you're using to find your Pi's IP address:

* On Linux (or Mac OS terminal), type **hostname -I** into a terminal window

* On Mac OS, go to **System Preferences** then **Network** and select your active network connection to view the IP address

* On Windows, go to the Control Panel, then under **Network and Sharing Center**, click **View network connections**, select your active network connection and click **View status of this conection** to view the IP address

Now you have the IP address of your computer, you will scan the whole subnet for other devices.

For example, if your IP adddress is 192.168.1.5, other devices will be at addresses like 192.168.1.2, 192.168.1.3, 192.168.1.4, etc. The notation of this subnet range is 192.168.1.0/24 (this covers 192.168.1.0 to 192.168.1.255).

Now use the **nmap** command with the **-sn** flag (ping scan) on the whole subnet range. This may take a few seconds:

> nmap -sn 192.168.1.0/24

Ping scan just pings all the IP addresses to see if they respond.

For each device that responds to the ping, the output shows the hostname and IP address.

Here you can see a device with hostname **raspberrypi** has IP address **192.168.1.xxx**.

## Access your raspberry pi over the internet

You can connect to your Raspberry Pi over the internet from another computer or a moblie device.



