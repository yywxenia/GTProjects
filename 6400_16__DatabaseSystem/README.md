# 6400Fall16Team013
OMS CS 6400 repo for our main project

## Development
Instructions and dependencies needed for development

### Dependencies:
You will need these things installed on your computer in order to boot the Vagrant box and test the database/server

- [Virtual Box](https://www.virtualbox.org/)
- [ChefDK](https://downloads.chef.io/chef-dk/mac/)
- [Vagrant](https://www.vagrantup.com/downloads.html)
    - [Vagrant Berkshelf Plugin](https://github.com/berkshelf/vagrant-berkshelf)
    - [Vagrant Omnibus Plugin](https://github.com/chef/vagrant-omnibus)

### Instructions
How to use this stuff:

1. From the project root directory execute the command `vagrant up` from your command line (assuming you have the above dependencies)
2. No errors should occur (none should which is the point of Vagrant, no ones computer should cause problems), let me know if they do
3. Once everything is complete execute the command `vagrant ssh` to log into the Virtual Machine
4. From there you can navigate to the project by executing `cd ../gatech/app` and if you list all files you'll see everything from our repo
5. MySQL is currently running now and should be reachable from the shell within the VM, login with the following:
    - user: root
    - password: password
    - Ex: `mysql -h 127.0.0.1 -u root --password='password'` should open the MySQL prompt
6. To start the flask server from the same directory execute `python applicaton/app.py`
    - You can now reach the app from your computer's browser, just go to `http://localhost:5000/`

Let me know if you have any issues.  Hopefully this setup allows us to develop locally without anyone's computer causing problems.

### Using the Application
 The password for each user is simply 'password'.


## Stuff needed to be done for final deliverable

- [x] Login
- [x] Menu
- [x] Add Resource
- [x] Add Incident
- [x] Search Resources
- [x] Search Results
- [x] Resource Status
- [x] Resource Report
