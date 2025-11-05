build-docker-image:
	docker build -t mauricioize/nutrition-api .

run:
	docker run -d -p 8080:8080 mauricioize/nutrition-api

composeup:
	docker compose up --build