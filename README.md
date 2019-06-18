# -= coming-soon =-

[![MPL License](http://img.shields.io/badge/license-MPL-blue.svg?style=flat)](https://www.mozilla.org/MPL/2.0/)
[![Build Status](http://img.shields.io/travis/SnootyMonkey/coming-soon.svg?style=flat)](https://travis-ci.org/SnootyMonkey/coming-soon)
[![Dependency Status](https://www.versioneye.com/user/projects/5482dabc3f594e3aca000147/badge.svg?style=flat)](https://www.versioneye.com/user/projects/5482dabc3f594e3aca000147)
[![Roadmap on Trello](http://img.shields.io/badge/roadmap-trello-blue.svg?style=flat)](https://trello.com/b/G8kY5MOf/coming-soon-https-github-com-snootymonkey-coming-soon)

A simple landing page email collector, but with 90% more parentheses than the leading brand.


## What is coming-soon

**coming-soon** is a simple "landing page" application that takes just a few minutes to setup on [Heroku](http://heroku.com/). With coming-soon you can quickly put up a page to publicize your new idea and collect email addresses of people who want to be notified when you are ready to launch.

It's powered by Clojure, ClojureScript and Redis.

You can see a [live demo](http://coming-soon-demo.heroku.com/) of coming-soon, or the screenshot below to get a sense of what it does.

![Silver Bullet Example](http://coming-soon-resources.s3.amazonaws.com/coming-soon-example.png)

(Here is the coming-soon [configuration file](https://github.com/SnootyMonkey/coming-soon/blob/master/examples/silver_bullet/config.edn) for the Silver Bullet example above.)

coming-soon is inspired by the Ruby app [LandingPad.rb](https://github.com/swanson/LandingPad.rb) by [Matt Swanson](https://github.com/swanson).


### What coming-soon can do

Here are just some of the Fantastic Features<sup>TM</sup> you can enjoy:

* Captures email, when they signed up, and what website they came from
* All the text on the landing page can be configured in a simple [config file](https://github.com/SnootyMonkey/coming-soon/blob/master/config.edn)
* Most of the styles can be configured as well using the same [config file](https://github.com/SnootyMonkey/coming-soon/blob/master/config.edn)
* User defined fonts, logo and background image using, you guessed it, the same [file](https://github.com/SnootyMonkey/coming-soon/blob/master/config.edn)
* Drop in your code snippet from your web analytics provider, such as Google Analytics, and you can track views and conversion rates for signing up
* Twitter, Facebook, GitHub and blog links are supported as a setting in the config file
* Go beyond the config file and change all the HTML and CSS to put whatever you'd like on the page
* The CSS is [Twitter Bootstrap](http://getbootstrap.com/)
* The icons are [Font Awesome](http://fortawesome.github.io/Font-Awesome/)
* All the third-party resources are hosted on a [CDN](http://en.wikipedia.org/wiki/Content_delivery_network)
* coming-soon makes no attempt to promote itself on your page... that's just tacky
* There's an [admin page](http://coming-soon-resources.s3.amazonaws.com/coming-soon-admin.png) to view sign-ups and export them as [JSON](http://en.wikipedia.org/wiki/JSON), [XML](http://en.wikipedia.org/wiki/XML) or [CSV](http://en.wikipedia.org/wiki/Comma-separated_values)
* There's no code that's not Clojure or ClojureScript, so... there's that


### Why you should NOT use coming-soon

Can you answer the question, "Why do you want to host your own landing page (even if Heroku is doing the hosting for you) with open source software rather than using a landing page service?"

If you don't have a great answer to that question, then there are **lots** of fully hosted alternatives for landing pages, it's a crowded space for these services, so there is likely a better option for you in this list:

* [Launch Rock](http://launchrock.co/) - the granddaddy of launch page services, tries to get users to share
* [Unbounce](http://unbounce.com) - lots of templates, A/B testing, drag n' drop WYSIWYG, on the expensive side
* [Strikingly](https://www.strikingly.com/) - for small general websites, but has email capture
* [Kickofflabs](http://www.kickofflabs.com/) - fairly comprehensive, widgets for external integrations, email campaigns
* [My Beta List](http://my.betali.st/) - also focused on user sharing
* [Launch Effect](http://launcheffectapp.com/) - Wordpress theme
* [Prefinery](http://www.prefinery.com/) - fairly comprehensive beta management, invite codes, viral sharing, feedback and surveys
* [ooomf](http://ooomf.com) - for mobile apps
* [LaunchGator](http://launch.deskgator.com/)
* [Mailchimp](http://mailchimp.com) - you probably think of them as the email list back end only, but they can serve up the email capture page too


### Why you SHOULD use coming-soon

It's very simple and it's very free. Just edit the config file, deploy it to Heroku for free, then get on with building your app. It's even faster than using many of the software as a service options.

As an example, prior Silver Bullet landing page and the following [Falklandsophile](http://falklandsophile.com) and [IdeaFerret](http://ideaferret.com) landing pages were configured using just the configuration file with **no** custom HTML, CSS or coding.

[![Falklandsophile Example](http://coming-soon-resources.s3.amazonaws.com/coming-soon-example2-small.png)](http://coming-soon-resources.s3.amazonaws.com/coming-soon-example2-full.png)

(Here is the [configuration file](https://github.com/SnootyMonkey/coming-soon/blob/master/examples/falklandsophile/config.edn) for the Falklandsophile example.)

[![IdeaFerret Example](http://coming-soon-resources.s3.amazonaws.com/coming-soon-example3-small.png)](http://coming-soon-resources.s3.amazonaws.com/coming-soon-example3-full.png)

(Here is the [configuration file](https://github.com/SnootyMonkey/coming-soon/blob/master/examples/ideaferret/config.edn) for the IdeaFerret example.)

If you want to go nuts on the templates and build some truly custom HTML and CSS for your landing page, then you have that option. You aren't locked into off-the-shelf templates or WYSIWYG editors or limited customization options like you are with many of the software as a service landing pages.

Here is the same IdeaFerret landing page with just [this simple custom CSS](https://github.com/SnootyMonkey/coming-soon/blob/master/examples/ideaferret/css/custom.css).

[![IdeaFerret Custom Example](http://coming-soon-resources.s3.amazonaws.com/coming-soon-example4-small.png)](http://coming-soon-resources.s3.amazonaws.com/coming-soon-example4-full.png)

coming-soon is also open source (as in free to do whatever you like with it), so if it only does 80% of what you want, and you prefer to do the other 20% in Clojure/ClojureScript, it's probably for you.


## What it takes to use it

coming-soon can be hosted on [Heroku](http://heroku.com) for free. Once you update the settings, just deploy it to Heroku and point a domain at it. That's all there is to it. You are then in business, and can get back to hacking on your app so that you have something to launch and send to all those email addresses you're collecting!


### Quick Start - 10 Steps to Heroku

**1) Signup for Heroku and setup the Heroku tools on your machine.**

If you haven't used Heroku before on your current computer, follow the [Getting Started with Heroku](https://devcenter.heroku.com/articles/quickstart) quick start steps to setup your free and instant account and the Heroku toolbelt on your local machine.

**2) Download coming-soon from GitHub using git.**

Run the following at the console.

```console
git clone https://github.com/SnootyMonkey/coming-soon.git
cd coming-soon
```

**3) Customize the configuration for your page.**

Edit the file called **config.edn** in a text editor.

You should see a **:coming-soon** block where you can enter admin access details for accessing your stored contacts. **PLEASE CHANGE YOUR USERNAME AND PASSWORD!**

You should also see a **:landing-page** block where you can configure the appearance of your landing page. There are guide images for changing the [text](http://coming-soon-resources.s3.amazonaws.com/coming-soon-configuration-text.png), [fonts](http://coming-soon-resources.s3.amazonaws.com/coming-soon-configuration-fonts.png), [images](http://coming-soon-resources.s3.amazonaws.com/coming-soon-configuration-images.png), and [colors](http://coming-soon-resources.s3.amazonaws.com/coming-soon-configuration-colors.png).

Update the copy to describe your application and change the colors, fonts, and other elements to customize your landing page.

Replace the logo file **resources/public/img/logo.png** with your own logo, or edit the config file to include an empty string **""** rather than **"/img/logo.png"** for the **:logo** setting.

If you want to use web analytics on your page, set the **:analytics** setting of the **:landing-page** block to **true**, and replace the contents of the **src/coming_soon/templates/analytics.html** file with the snippet of code provided by your analytics provider.

**4) Commit the configuration changes for your app.**

Once you have completed editing **config.edn** to add your app's settings, run the following commands in your coming-soon folder to commit the changes to your local [git](http://git-scm.com/) repository:

```console
git add .
git commit -m "Setting up my landing page."
```

**5) Create your Heroku app.**

If you haven't used Heroku before on your current computer, login to Heroku from the commandline. Provide the email and password you used when creating your Heroku account when you are prompted for them. (If you have used Heroku from the commandline on your current computer, you can skip the login).

```console
heroku login
```

Now create the new Heroku app and provide it a custom buildpack that can handle our ClojureScript compilation.

```console
heroku create
heroku config:add BUILDPACK_URL=https://github.com/kolov/heroku-buildpack-clojure
```

**6) Attach a Redis instance to your app.**

As of this writing, there are 2 options for free Redis on Heroku.

To use [Redis Cloud](https://addons.heroku.com/rediscloud), run this command:

```console
heroku addons:add rediscloud:20
```

To use [Redis To Go](https://addons.heroku.com/redistogo), run this command:

```console
heroku addons:add redistogo
```
As of this writing, there are 2 additional Redis options on Heroku that do not have a free tier.

To use [openredis](https://addons.heroku.com/openredis), run this command:

```console
heroku addons:add openredis:micro
```

To use [RedisGreen](https://addons.heroku.com/redisgreen), run this command:

```console
heroku addons:add redisgreen:development
```

In your text editor comment in the **:redis-env-variable** line in the **:redis** section of the **config.edn** file that matches the Redis provider you selected. Then commit your change to git.

```console
git add .
git commit -m "Selecting my Redis provider."
```

**7) Push the code of your app up to the Heroku cloud.**

```console
git push heroku master
```

**8) Congratulations! Now test out your landing page.**

Launch your app in your browser.

```console
heroku open
```

This will open a browser and you should see your landing page. Note the ugly URL Heroku created for you so you can get back to your page.

Test Redis connectivity by adding /redis-test to your browser's URL after the URL for your app on Heroku

For instance, if you app is **http://your-heroku-machine-name.heroku.com/** then you should load **http://your-heroku-machine-name.heroku.com/redis-test**

You should see: "Connection to Redis is: OK"

Note:
In case you see in browser's console error that coming-soon.js is not found, you need to build your project locally. Add `resources/public/js/` directory and push it to heroku as well.

Go back to your landing page by removing the /redis-test from the URL and enter in your email address to test signing up for your own app.

To view the contact information that's been entered into your landing page, navigate to **http://your-heroku-machine-name.heroku.com/contacts**.  You will need to enter the admin username and password that you setup in **config.edn**.

You should see a table listing the email and referral URL for everyone that has signed up for your app.

[![Admin Example](http://coming-soon-resources.s3.amazonaws.com/coming-soon-admin-small.png)](http://coming-soon-resources.s3.amazonaws.com/coming-soon-admin-full.png)

**9) Setup your custom domain.**

You will probably want a custom domain rather than Heroku's default. Follow [Heroku's instructions](http://devcenter.heroku.com/articles/custom-domains) to setup your domain to point to your brand-new landing page.

**10) Now get back to coding your app!**


### It looks good, but I want it to be more blue

You can modify any of the HTML, CSS and images to customize your page. Just remember, you need to push any changes to Heroku so your live page will be updated.

```console
git add .
git commit -m 'Made the HTML and CSS more pretty.'
git push heroku
```


### It works well, but I want it to make my coffee too

You can modify any of the Clojure and ClojureScript code to customize the behavior of your landing page. Just remember, you need to push any changes to Heroku so your live page will be updated.

To build the ClojureScript code:

Note: do not forget to upgrade lein
```console
lein upgrade
```
And build it.

```console
lein cljsbuild once
```

To watch the ClojureScript code and build it each time it changes:

```console
lein cljsbuild auto
```

To run coming-soon locally:

```console
lein start
```

## Tests

Tests are run in continuous integration of the `master` and `dev` branches on [Travis CI](https://travis-ci.org/SnootyMonkey/coming-soon):

[![Build Status](http://img.shields.io/travis/SnootyMonkey/coming-soon.svg?style=flat)](https://travis-ci.org/SnootyMonkey/coming-soon)

To run the tests locally:

```console
lein test!
```

## Frequently Asked Questions

Here are some questions about coming-soon that I get asked all the time (or maybe I just made them all up).

**Q:** Can I host it somewhere other than Heroku?
**A:** Sure, nothing about coming-soon is Heroku specific.

**Q:** Why is it so slow to load the landing page sometimes?
**A:** Unless you are paying Heroku to host at least 2 dynos, you are subject to [idling](https://devcenter.heroku.com/articles/dynos#dyno-idling). This means that after an hour, your app will be spun down to save resources. The next unlucky soul to access your landing page will have to wait for 10 seconds or so for your app to spin back up. You can solve this by paying Heroku for 2 web dynos or by using a server monitoring service that pings your landing page more frequently than once an hour.

**Q:** How many signups can I store before my Redis instance runs out of space?
**A:** That's a very optimistic question. I like you; you've got gumption. A good rule of thumb is that an empty Redis instance is ~1MB and coming-soon uses (very conservatively) ~.5MB per 1,000 registrations. The .5MB per 1,000 registrations estimate is providing for a fairly long and unique referrer URL for every user, a fairly long email addresses for every user, and 25% overhead for memory fragmentation, so your actual results will likely be better. Assuming the conservative rule of thumb, and a free 20MB Redis service, you'll get over 38,000 registrations before it fills up and you have to start paying for Redis.

**Q:** Who made this treasure?
**A:** coming-soon is written by Sean Johnson, the founder of [Snooty Monkey](http://snootymonkey.com/).

**Q:** How can I ever repay you for creating such a treasure?
**A:** It's not required by the license terms, but please [drop me a note](http://snootymonkey.com/contact/) and let me know the URL of your landing page so I can take a look.

**Q:** Can I use it for X?
**A:** coming-soon is licensed with the [MIT license](https://github.com/SnootyMonkey/coming-soon/blob/master/MIT-LICENSE.txt), so you are free to use it pretty much however you'd like, in accordance with the license terms.

**Q:** Can I add X to it?
**A:** Sure, please fork coming-soon on GitHub if you'd like to enhance it.

**Q:** Can I contribute my enhancements back to the project?
**A:** Sure, send me your pull requests if you'd like to contribute back your enhancements. I promise to look at every pull request and incorporate it, or at least provide feedback on why if I won't.

## Links

* [GitHub Project](http://github.com/SnootyMonkey/coming-soon)
* [Change Log](http://github.com/SnootyMonkey/coming-soon/blob/master/CHANGELOG.md)
* [Issue Tracker](http://github.com/SnootyMonkey/coming-soon/issues)
* [Live Demo Example](http://coming-soon-demo.herokuapp.com)
* [Live Admin Demo](http://coming-soon-admin.herokuapp.com/contacts) - login as admin / admin
* [IdeaFerret](http://ideaferret.com) - a coming-soon powered landing page
* [Falklandsophile](http://falklandsophile.com) - another coming-soon powered landing page
* [OPENcompany](http://opencompany.io) - yet another coming-soon powered landing page

## License

coming-soon is distributed under the [Mozilla Public License v2.0](http://www.mozilla.org/MPL/2.0/).

Copyright © 2013-2017 [Snooty Monkey, LLC](http://snootymonkey.com/)
