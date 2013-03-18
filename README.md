# coming-soon

A simple landing page email collector, but with 90% more parentheses than the leading brand.

## What is coming-soon?

**coming-soon** is a simple Clojure/ClojureScript/Redis powered "landing page" application that takes just a few minute to setup. With coming-soon you can quickly put up a page to publicize your new idea, and to collect email addresses of people who want to be notified when you are ready to launch.

![](http://coming-soon-resources.s3.amazonaws.com/coming-soon-example.png)

All the text on the landing page can be configured in a simple text file. Most of the style elements can be configured as well using the same file, and of course you can go beyond the configuration and change all the HTML and CSS to put whatever you'd like on the page.

The above landing page and the following landing pages were configured using just the configuration file.

![](http://coming-soon-resources.s3.amazonaws.com/coming-soon-example-2.png)

![](http://coming-soon-resources.s3.amazonaws.com/coming-soon-example-3.png)

Google Analytics is supported as a setting, so you can track views and conversion rates for signing up.

Twitter, App.net and Facebook page links are supported as a setting.

coming-soon is inspired by the Ruby app [LandingPad.rb](https://github.com/swanson/LandingPad.rb) by [Matt Swanson](https://github.com/swanson).

## What do I need to use it?

coming-soon can be hosted on Heroku for free, and uses Redis to store the submitted contacts. Once you update the settings, just deploy it to Heroku and point a domain at it. That's all there is to it. You are then in business, and can get back to hacking on your app so that you have something to launch and send to all those email addresses you're collecting!

## Quick Start - 10 Steps to Heroku

1. Make sure you have the Heroku pre-reqs: [http://devcenter.heroku.com/articles/quickstart#prerequisites]()

1. Setup an account on [Heroku](heroku.com) (you can use a free account)  

	Instructions: [http://devcenter.heroku.com/articles/quickstart]()  
  
1. Download coming-soon:

	https://github.com/SnootyMonkey/coming-soon/archive/master.zip

1. Unzip coming-soon and navigate to the directory it creates:

	unzip coming-soon
	cd coming-soon

1. Open 'config.edn' in a text editor.  You should see a 'db' block where you can enter the details for your database connection, a 'coming-soon' block where you can enter admin access details, and a 'landing-page' block where you can configure the appearance of the landing page (your site's name, a summary, colors, background, etc).

1. The 'coming-soon' block is where you set the admin username and password for accessing your stored contacts -- **PLEASE CHANGE THIS!**

1. You can set your Google Analytics tracking id in the 'landing-page' block if you have an account.

1. Once you have edited 'config.edn' to add your app's settings, run the following commands from your project folder:

	git init
  git add .
  git commit -m "setting up my landing page"

1. Now create your Heroku app by running from your project folder:

	heroku create

1. Now run `git push heroku master` to push the code to your Heroku app.  Once it's finished, run `heroku open` to launch a browser and go to your app.  

	You should see a landing page and be able to enter in an email address.

1.	To view the contact information that's been entered into your landing page, navigate to **http://your-heroku-machine-name.heroku.com/contacts**.  You will need to enter the admin username and password that you setup in 'config.edn'.  

	You should see a table listing the name, type and referral URL for anyone that has signed up for your app.

	![](http://coming-soon-resources.s3.amazonaws.com/coming-soon-admin.png)

1.  You will probably want a custom domain, following the instructions here [http://devcenter.heroku.com/articles/custom-domains]() to setup your domain to point to your brand-new landing page.

## Guide to the configuration file

(todo list and explain the configuration settings)

## It looks good, but I want it to be more blue

You can modify any of the HTML, CSS and images to customize your page. Just remember, you need to push any changes to Heroku so your live page will be updated.

(todo list and explain HTML and CSS)

## It works well, but I want it to make my coffee too

You can modify any of the Clojure and ClojureScript code to customize the behavior of your landing page. Just remember, you need to push any changes to Heroku so your live page will be updated.

(todo list and explain important code and development steps)

lein ring server-headless
lein cljsbuild once
lein cljsbuild auto

## Who made this treasure? Can I use it? Can I contribute enhancements?

coming-soon is written by Sean Johnson, the founder of [Snooty Monkey](http://snootymonkey.com).

coming-soon is licensed with the [MIT license](https://github.com/SnootyMonkey/coming-soon/blob/master/MIT-LICENSE.txt), so you are free to use it pretty much however you'd like, in accordance with the license terms.

Please do fork coming-soon if you'd like to enhance it, and send me your pull requests if you'd like to contribute back your enhancements. I promise to look at every pull request and incorporate it, or at least provide feedback if I won't.