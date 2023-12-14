This is front end project for Registration and login for a user. In this project I used vue 3 as front end technology.

 # 1. Vue 3 Installation Guidelines

## 1.1 Install Node.js & NPM Package

 __Download Node.js and NPM (Node Package Manager) on your PC as the main action.__

You may determine whether they are already downloaded by navigating to the Terminal/Command prompt window on your system and executing a few instructions.

To determine the model of Node.js version,

##### node -v

If it is currently downloaded, ensure that it is version 10 or higher.

To confirm the npm, execute the below  code in the  terminal.

##### npm -v

If they are not downloaded, you will see a warning like “command not found” when performing node and npm versions test commands. Therefore, you will be required to install these.

Navigate to the Node.js portal in the internet browser [Node.js](https://nodejs.org/en). You will find two variants; It is recommended choosing LTS, the better reliable version, that is usually preferred for most visitors.

![Node js Home Page](https://github.com/praveen-riseslabs/fullstack-venkataraman-vuejs/assets/152584709/c0347e2c-7cef-4d12-a20c-222469d90543)

Clicking the LTS button initiates the Node.js package file installing on the PC.

To download the file, double-click the file to open the installation window. Installing Node.js is the same as installing any other software.

Along with node.js, you may also download npm since they do not need to be installed individually.

Click Continue, Continue,

Then click Agree and Install, that will request the administrator passcode. After entering the passcode, it will take a bit of time to finish the setup.

The following step involves installing Vue CLI.

## 1.2 Install Vue CLI

Launch Terminal and execute the following command:

##### npm install -g @vue/cli

A few moments will be required to finish the installation. 

If it states that users do not get authorization, rerun the command with the sudo prefix, which will prompt you to provide your administrator passcodes, and you will be good to go.

##### sudo npm install -g @vue/cli

To build a vue 3 application, a vue cli version of 4.5 or higher is required.

If you’ve previously downloaded vue cli, you may use the below command to determine its version.

###### vue —version

If you have a release that is less than 4.5, you may update it by using the command here.

##### npm upgrade —next

After completion of vue installation follow the below steps to run the project.

# Steps:

### Clone the Project:

Use git clone to get a copy of the project onto your local machine by using below command.

#### git clone repository-url

### Install Dependencies:

Navigate to the project directory and install the required dependencies using npm or yarn.

#### cd project-folder

#### npm install

### Run the Development Server:

After installing the dependencies, start the development server by using below command.

#### npm run serve

### Access the Application:

Open your browser and navigate to the URL where the Vue.js application is running. By default, it might be http://localhost:8080.

### Login page:

![login-page](https://github.com/praveen-riseslabs/fullstack-venkataraman-vuejs/assets/152584709/56b4f57f-b9ed-4c49-b0dc-e6e59edba15f)

### Registration Page


![Registration_page](https://github.com/praveen-riseslabs/fullstack-venkataraman-vuejs/assets/152584709/ae0c4f06-8dce-4865-845a-94fb1f0b01ae)

