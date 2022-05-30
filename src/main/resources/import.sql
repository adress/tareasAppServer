insert into usuarios (username, password, enabled) values ('adress', '$2a$10$hN4LHYbLv8lWslDWUdAQQ.7SptifvRRkWPzopFBhBJk9.QvVFH5Wm', 1);
insert into usuarios (username, password, enabled) values ('pueba', '$2a$10$KKXuIsTFnv.q.4l.fqvMI.YiKeDt8LVEh1xFxu5/qynpSWWzCto9W', 1);
insert into usuarios (username, password, enabled) values ('aranda', '$2a$10$hN4LHYbLv8lWslDWUdAQQ.7SptifvRRkWPzopFBhBJk9.QvVFH5Wm', 1);
insert into usuarios (username, password, enabled) values ('tareas', '$2a$10$KKXuIsTFnv.q.4l.fqvMI.YiKeDt8LVEh1xFxu5/qynpSWWzCto9W', 1);
insert into usuarios (username, password, enabled) values ('yami', '$2a$10$KKXuIsTFnv.q.4l.fqvMI.YiKeDt8LVEh1xFxu5/qynpSWWzCto9W', 1);

insert into roles(nombre) values ('ROLE_USER');

insert into usuarios_roles(usuario_id, rol_id) values (1, 1);
insert into usuarios_roles(usuario_id, rol_id) values (2, 1);
insert into usuarios_roles(usuario_id, rol_id) values (3, 1);
insert into usuarios_roles(usuario_id, rol_id) values (4, 1);

insert into tareas(titulo, finalizada, fecha_creacion, fecha_vencimiento, usuario_id, descripcion)values ('title 2', 1, '2021-12-20', '2022-01-11', 3, 'Crear el modelo entidad relacion de la base de datos');
insert into tareas(titulo, finalizada, fecha_creacion, fecha_vencimiento, usuario_id, descripcion)values ('title 3', 1, '2021-12-20', '2022-01-03', 2, 'Faucibus suscipit convallis massa erat vivamus, id interdum libero auctor. Nostra libero torquent vulputate id hendrerit aliquet lacus viverra eros ullamcorper netus vitae, orci habitant a arcu sem placerat bibendum magna in taciti porta.');
insert into tareas(titulo, finalizada, fecha_creacion, fecha_vencimiento, usuario_id, descripcion) values ('title 4', 0, '2021-12-20', '2022-05-02', 5,'Praesent eleifend lectus nisi massa quis nunc, vitae ullamcorper risus ligula platea quisque, per ut habitasse semper mi. Laoreet vel est id viverra per, ac porttitor fusce rhoncus, elementum torquent malesuada ridiculus.');
insert into tareas(titulo, finalizada, fecha_creacion, fecha_vencimiento, usuario_id, descripcion) values ('title 5', 1, '2021-12-20', '2022-10-15', 2, 'Lorem ipsum dolor sit amet consectetur adipiscing elit nisi non curabitur, morbi viverra sodales mattis,');
insert into tareas(titulo, finalizada, fecha_creacion, fecha_vencimiento, usuario_id, descripcion) values ('title 6', 0, '2021-12-20', '2022-05-22', 1, 'Praesent eleifend lectus nisi massa quis nunc, vitae ullamcorper risus ligula platea quisque, per ut habitasse semper mi. Laoreet vel est id viverra per, ac porttitor fusce rhoncus, elementum torquent malesuada ridiculus.');
insert into tareas(titulo, finalizada, fecha_creacion, fecha_vencimiento, usuario_id, descripcion) values ('title 7', 0, '2021-12-20', '2022-05-21', 4, 'Lorem ipsum dolor sit amet consectetur adipiscing elit nisi non curabitur, morbi viverra sodales mattis consequat nec cras sem habitant. ');
insert into tareas(titulo, finalizada, fecha_creacion, fecha_vencimiento, usuario_id, descripcion) values ('title 8', 1, '2021-12-20', '2022-05-10', 1, 'Lorem ipsum dolor sit amet consectetur adipiscing elit nisi non curabitur, morbi viverra sodales mattis consequat nec cras sem habitant. ');