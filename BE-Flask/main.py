from flask import Flask, request
from flask_cors import CORS
from s3 import *
app = Flask(__name__)
cors = CORS(app, resources={r"*": {"origins": "*"}})

bucket_name = "s3ffmpegtest"
s3 = s3_connection()
@app.route("/api/v2/concateVideo", methods=['GET'])
def concateVideo():
    studio_id = request.args.get('studio_id')
    film_id = request.args.get('film_id')
    object_list = make_objects_list(s3, bucket_name)
    film_scene = make_film_scene(studio_id, object_list)
    url = make_film(s3, film_scene, bucket_name, studio_id, film_id)
    return url;

if __name__ == '__main__':
    app.run('0.0.0.0', port=80, debug=True)


