#Newgrounds Gamerscore Leaderboard

### What is it?

This is an application used to automate the process of generating the newgrounds.com Gamerscore Leaderboard forum posts. When provided with Xbox GamerTags, the application will retreive the gamer's current amount of Gamerscore by pulling the data from the associated xbox.com profile page. This information is then sorted and used to generate an updated leaderboard, which can easily be copied to the user's clipboard and pasted as a forum post.

### Current state?

Currently the application should be functional, however there are still numerous upgrades and fixes that need to be implimented.

### Requirements

This application makes use of the <a href="http://jsoup.org/download">jsoup</a> library to parse HTML. A copy of jsoup 1.7.2 is included in the /libs directory.

### License

The MIT License (MIT)

Copyright (c) 2013 Dean Thomson

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
