So I did not get this project completed to my satisfaction, but I did promise to
finish by today, so I am bookmarking it here.  I will continue working on it on 
my own, so I learn all the things I need for a basic understanding.  My problem 
is not the coding per se, but the Lambda interface to AWS - getting everything 
permissioned and exposed to the user to run.

That said, I did learn about AWS and a lot of this I didn't previously know

* creating an AWS account 
* getting an account ID and secret
* setting up the credentials file locally
* creating users
* groups
* permissioning all the things!!! <--- this was a toughie
* roles
* using the CLI for AWS and SAM
* creating a project from the commandline
* setting up my AWS Gateway
* creating the Lambdas with and without triggers
* s3 buckets
* ... many more things

You'll see in the code that my idea is to take in the "driver" file from s3 bucket as a list 
of strings.  Basically, my thought was to have one string per line, but they would be numbers
that I could convert in the project and run through each lambda function one after another or 
individually.  I have a function for each mean, mode, median and the calculations
are proved out in the spock table tests specification file.

Sorry, I have to leave this for now - I have other commitments, but I am glad I 
took the time to learn this much.  I will continue working on this or my own
edification - so thanks for giving me an objective and a time cutoff.