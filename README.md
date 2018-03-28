If you see this. It means that I personally invited you to this repository. Please don't steal my client tokens :)

## Prerequisites

### put.io

You need an active account at put.io. This will cost you $ 10 / month so only keep going if you really want this.
Put.io is great with or without this application.

- Create an account on [put.io](put.io)
- Go to Account -> MANAGE YOUR APPLICATIONS -> CREATE A NEW OAUTH APP
- Once done, click on the key icon under the application name.
- Generate an OAUTH token. This will be called PUTIO_TOKEN below.



### Trakt.tv
##### Create an account on [trakt.tv](trakt.tv)
##### Go to settings and create an "application" to get a CLIENT_ID and CLIENT_SECRET for the api.
##### Get a user TRAKT_ACCESS_TOKEN using this CLIENT_ID:

Replace CLIENT_ID with yours then open a browser and go to

```sh
https://api.trakt.tv/oauth/authorize?response_type=code&client_id=CLIENT_ID&redirect_uri=urn:ietf:wg:oauth:2.0:oob
```

You will be given a temporary code TEMP_CODE. Replace CLIENT_ID, CLIENT_SECRET and TEMP_CODE then run this in a terminal
```sh
curl -X POST -d "client_id=CLIENT_ID&client_secret=CLIENT_SECRET&grant_type=authorization_code&code=TEMP_CODE&redirect_uri=urn:ietf:wg:oauth:2.0:oob" https://api.trakt.tv/oauth/token
```
Copy paste _access_token_ from the response. Put this in start.sh as TRAKT_ACCESS_TOKEN

## Running Locally

Make sure you have Java and Maven installed.

```sh
git clone https://github.com/johantiden/tv-time.git
cd tv-time
```

##### Edit start.sh and insert the tokens you created above.

```sh
./start.sh
```

Your app should now be running on [localhost:5000](http://localhost:5000/).

## FAQ
### The app works but nothing is happening?
You have to manually add shows on trakt.tv for them to show up. Currently, the way to do that is to check at least one episode of that show as watched.

So go to [trakt.tv](trakt.tv), login and find a show to watch, Then find an episode you have seen (or an episode you don't want to see e.g. one of the 'specials'). Mark it as watched. It will show up in the listing after a couple of minutes.

This might improve in the future but for now, it's good enough.