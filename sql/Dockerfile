FROM mysql:5.7
ADD ./my.cnf /etc/mysql/conf.d/my.cnf
COPY init/* /docker-entrypoint-initdb.d/
CMD ["mysqld"]
