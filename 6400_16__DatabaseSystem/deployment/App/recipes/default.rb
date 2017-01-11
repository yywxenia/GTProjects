
# update linux environment
execute "apt-get update" do
    command "apt-get update"
end

# python runtime version and app requirements
python_runtime '2'
pip_requirements '/home/gatech/app/requirements.txt'

# install python mysql connector used by dbConnect
apt_package "python-mysql.connector" do
	action :install
end

# install and setup MySQL
mysql_service 'mysql_db' do
    port '3306'
    version '5.6'
    initial_root_password 'password'
    action [:create, :start]
end

# create main database
execute "create main db" do
    command "mysql -h 127.0.0.1 -u root --password='password' -e 'CREATE DATABASE IF NOT EXISTS emergency_response_system'"
end

# run creation script on database
execute 'run creation script' do
    command "mysql -h 127.0.0.1 -u root --password='password' emergency_response_system < /home/gatech/app/application/sql/creation_script.sql"
end

# run creation script on database
execute 'run creation script' do
    command "mysql -h 127.0.0.1 -u root --password='password' emergency_response_system < /home/gatech/app/application/sql/insert_statements_script.sql"
end
