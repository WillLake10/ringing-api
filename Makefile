git-config:
	git config --local user.email "william.j.lake2000@gmail.com"
	git config --local user.name "Will Lake"

check-git-config:
	git config --local user.email
	git config --local user.name

logs:
	tail -f logs/ringing-api.log