cd ..
git checkout master
git branch -D wzy
git pull upstream master
git checkout -b wzy

git status

git add .
git status
git commit -m "%date% - wzy"
git push origin wzy