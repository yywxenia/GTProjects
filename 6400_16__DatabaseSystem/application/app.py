import os.path
import sys

# necessary to ensure modules are found

sys.path.append(os.path.join(os.path.dirname(__file__), '..'))

from flask import Flask, session, redirect, url_for, request


from application.resource_status import get_resource_status_route, \
    return_resource_in_use, deploy_resource_from_request, \
    cancel_resource_request_for_resource, \
    reject_resource_from_request, cancel_request_for_resource_repair


from application.login import index_route
from application.add_resource import add_resource_route
from application.menu import menu_route
from application.resource_report import resource_report_route
from application.search_resources import search_resources_route
from application.add_incident import add_incident_route
from application.search_results import user_request, owner_deploy, owner_repair

app = Flask(__name__)
app.secret_key = os.urandom(24)


@app.before_request
def before_request():
    """
    Before any request check if the user is already logged in.
    """
    if request.endpoint == 'login' or 'static' in request.url:
        return

    # if user is not already logged in redirect to login pages
    if 'username' not in session and 'name' not in session:
        return redirect(url_for('login'))


@app.route('/', methods=['GET', 'POST'])
def login():
    return index_route()


@app.route('/menu', methods=['GET'])
def menu():
    return menu_route()


@app.route('/addresource', methods=['GET', 'POST'])
def add_resource():
    return add_resource_route()


@app.route('/addincident', methods=['GET', 'POST'])
def add_incident():
    return add_incident_route()


@app.route('/searchresources', methods=['GET', 'POST'])
def search_resources():
    return search_resources_route()


@app.route('/resource/request/', methods=['GET'])
def search_results_resource_request():
    resource_id = request.args.get('resource-id')
    incident_id = request.args.get('incident-id')
    return user_request(inc_id=incident_id, res_id=resource_id )


@app.route('/resource/repair/', methods=['GET'])
def search_results_resource_repair():
    resource_id = request.args.get('resource-id')
    return owner_repair(res_id=resource_id)


@app.route('/resource/deploy/', methods=['GET'])
def search_results_resource_deploy():
    resource_id = request.args.get('resource-id')
    incident_id = request.args.get('incident-id')

    return owner_deploy(inc_id=incident_id, res_id=resource_id)


@app.route('/resource-status', methods=['GET'])
def resource_status():
    return get_resource_status_route()


@app.route('/resource/return', methods=['GET'])
def return_resource():
    return return_resource_in_use()


@app.route('/resource-request/cancel', methods=['GET'])
def cancel_resource_request():
    return cancel_resource_request_for_resource()


@app.route('/resource/deploy', methods=['GET'])
def deploy_resource():
    return deploy_resource_from_request()


@app.route('/resource/reject', methods=['GET'])
def reject_resource():
    return reject_resource_from_request()


@app.route('/resource/repair/cancel', methods=['GET'])
def cancel_resource_repair():
    return cancel_request_for_resource_repair()


@app.route('/resourcereport', methods=['GET'])
def resource_report():
    return resource_report_route()


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5000)
