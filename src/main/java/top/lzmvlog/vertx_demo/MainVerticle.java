package top.lzmvlog.vertx_demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.tracing.TracingPolicy;
import io.vertx.ext.web.Router;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.templates.SqlTemplate;
import top.lzmvlog.vertx_demo.model.Student;

import java.util.Collections;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
//    vertx.createHttpServer().requestHandler(req -> {
//      req.response()
//        .putHeader("content-type", "text/plain")
//        .end("Hello from Vert.x!");
//    }).listen(8888, http -> {
//      if (http.succeeded()) {
//        startPromise.complete();
//        System.out.println("HTTP server started on port 8888");
//      } else {
//        startPromise.fail(http.cause());
//      }
//    });

//    // Create a Router
//    Router router = Router.router(vertx);
//
//    // Mount the handler for all incoming requests at every path and HTTP method
//    router.route().handler(context -> {
//      // Get the address of the request
//      String address = context.request().connection().remoteAddress().toString();
//      // Get the query parameter "name"
//      MultiMap queryParams = context.queryParams();
//      String name = queryParams.contains("name") ? queryParams.get("name") : "unknown";
//      // Write a json response
//      context.json(
//        new JsonObject()
//          .put("name", name)
//          .put("address", address)
//          .put("message", "Hello " + name + " connected from " + address)
//      );
//    });
//
//    // Create the HTTP server
//    vertx.createHttpServer()
//      // Handle every request using the router
//      .requestHandler(router)
//      // Start listening
//      .listen(8888)
//      // Print the port
//      .onSuccess(server ->
//        System.out.println(
//          "HTTP server started on port " + server.actualPort()
//        )
//      );

    HttpServer server = vertx.createHttpServer();
    Router router = Router.router(vertx);
//    router.route().handler(ctx -> {
//      // This handler will be called for every request
//      HttpServerResponse response = ctx.response();
//      response.putHeader("content-type", "text/plain");
//      // Write to the response and end it
//      response.end("Hello World from Vert.x-Web!");
//    });

    server.requestHandler(router).listen(8080, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8080");
      } else {
        startPromise.fail(http.cause());
      }
    });

//    Route route = router.route("/some/path/");
//    route.handler(ctx -> {
//      HttpServerResponse response = ctx.response();
//      // enable chunked responses because we will be adding data as
//      // we execute over other handlers. This is only required once and
//      // only if several handlers do output.
//      response.setChunked(true);
//      response.write("route1\n");
//      // Call the next matching route after a 5 second delay(延迟5秒后呼叫下一条匹配路线)
//      ctx.vertx().setTimer(5000, tid -> ctx.next());
//    });
//
//    route.handler(ctx -> {
//      HttpServerResponse response = ctx.response();
//      response.write("route2\n");
//      // Call the next matching route after a 5 second delay(延迟5秒后呼叫下一条匹配路线)
//      ctx.vertx().setTimer(5000, tid -> ctx.next());
//    });
//
//    route.handler(ctx -> {
//      HttpServerResponse response = ctx.response();
//      response.write("route3");
//
//      // Now end the response
//      ctx.response().end();
//    });

//    router
//      .get("/some/path")
//      // this handler will ensure that the response is serialized to json
//      // the content type is set to "application/json"
//      .respond(
//        ctx -> Future.succeededFuture(new JsonObject().put("hello", "world")));

//    router
//      .get("/some/path")
//      // this handler will ensure that the Pojo is serialized to json
//      // the content type is set to "application/json"
//      .respond(
//        ctx -> Future.succeededFuture(new Student()));

//    router
//      .get("/some/path")
//      .respond(
//        ctx -> ctx
//          .response()
//          .putHeader("Content-Type", "text/plain")
//          .end("hello world!"));

//    router
//      .get("/some/path")
//      // in this case, the handler ensures that the connection is ended
//      .respond(
//        ctx -> ctx
//          .response()
//          .setChunked(true)
//          .write("Write some text..."));

//    router.route().blockingHandler(ctx -> {
//      // Do something that might take some time synchronously
//      service.doSomethingThatBlocks();
//      // Now call the next handler
//      ctx.next();
//    });

//    router.post("/some/endpoint").handler(ctx -> {
//      ctx.request().setExpectMultipart(true);
//      // 现在调用下一个处理程序
//      ctx.next();
//    }).blockingHandler(ctx -> {
//      // 做一些阻塞操作
//    });

//    Route route = router.route().path("/some/path/");
//    route.handler(ctx -> {
    // 此处理程序将被以下请求路径调用:
    // This handler will be called for the following request paths:

    // `/some/path/`
    // `/some/path//`
    //
    // but not:
    // `/some/path` the end slash in the path makes it strict
    // `/some/path/subdir`
//      HttpServerResponse response = ctx.response();
//      response.write("/some/path/");
//      ctx.response().end();
//    });

// paths that do not end with slash are not strict
// this means that the trailing slash is optional
// and they match regardless
//    Route route2 = router.route().path("/some/path");
//
//    route2.handler(ctx -> {
//      // This handler will be called for the following request paths:
//
//      // `/some/path`
//      // `/some/path/`
//      // `/some/path//`
//      //
//      // but not:
//      // `/some/path/subdir`
//    });

//    Route route = router.route().path("/some/path/*");
//    route.handler(ctx -> {
//      // 此处理程序将被以下请求路径调用:
//      // `/some/path/`, e.g.
//      // `/some/path/`
//      // `/some/path/subdir`
//      // `/some/path/subdir/blah.html`
//
//      // but not:
//      // `/some/path` 该路径是严格的，因为它以斜杠结束
//      // `/some/bath`
//      HttpServerResponse response = ctx.response();
//      response.putHeader("content-type", "text/plain");
//      response.end("/some/path/*");
//    });

//    router
//      .route(HttpMethod.POST, "/catalogue/products/:productType/:productID/")
//      .handler(ctx -> {
//        String productType = ctx.pathParam("productType");
//        String productID = ctx.pathParam("productID");
//        HttpServerResponse response = ctx.response();
//        response.putHeader("content-type", "text/plain");
//        response.end(productID + "--" + productType);
//      });

//    Route route = router.route(HttpMethod.POST, "/some/path/");
//
//    route.handler(ctx -> {
//      // 对于以/some/path/开头的URI路径的任何POST请求，都会调用此处理程序
//      HttpServerResponse response = ctx.response();
//      response.putHeader("content-type", "text/plain");
//      response.end("method--/some/path/");
//    });

//    router
//      .route("/some/path/")
//      .order(1)
//      .handler(ctx -> {
//        HttpServerResponse response = ctx.response();
//        response.write("route1\n");
//        // Now call the next matching route
//        ctx.next();
//      });
//    router
//      .route("/some/path/")
//      .order(0)
//      .handler(ctx -> {
//        HttpServerResponse response = ctx.response();
//        // 启用分块响应，因为我们将在执行其他处理程序时添加数据。
//        // 仅一次且仅当多个处理程序进行输出时才需要这样做。
//        response.setChunked(true);
//        response.write("route2\n");
//        // Now call the next matching route
//        ctx.next();
//      });
//    router
//      .route("/some/path/")
//      .order(2)
//      .handler(ctx -> {
//        HttpServerResponse response = ctx.response();
//        response.write("route3");
//        // Now end the response
//        ctx.response().end();
//      });


//    ---------------------MySQL----------------------

    MySQLConnectOptions connectOptions = new MySQLConnectOptions()
      .setPort(3306)
      .setHost("127.0.0.1")
      .setDatabase("myschool")
      .setUser("root")
      .setPassword("Root5683@")
      // 设置重新连接尝试的值
      .setReconnectAttempts(2)
      // 设置重新连接间隔
      .setReconnectInterval(100)
      // 当Vert.x启用了跟踪时，为客户端行为设置跟踪策略。
      .setTracingPolicy(TracingPolicy.ALWAYS);

    // Pool options
    PoolOptions poolOptions = new PoolOptions()
      .setMaxSize(5);

//    String connectionUri = "mysql://root:Root5683@@127.0.0.1:3306/myschool";
//    MySQLPool client = MySQLPool.pool(connectionUri);

    MySQLPool pool = MySQLPool.pool(vertx, connectOptions, poolOptions);

    // A simple query
//    client
//      .query("SELECT * FROM student WHERE id='1'")
//      .execute(ar -> {
//        if (ar.succeeded()) {
//          RowSet<Row> result = ar.result();
//          System.out.println("Got " + result.size() + " rows ");
//        } else {
//          System.out.println("Failure: " + ar.cause().getMessage());
//        }
//        // Now close the pool
//        client.close();

//    client
//      .preparedQuery("SELECT * FROM student WHERE id=?")
//      .execute(Tuple.of("1"), ar -> {
//        if (ar.succeeded()) {
//          RowSet<Row> rows = ar.result();
//          System.out.println("Got " + rows.size() + " rows ");
//        } else {
//          System.out.println("Failure: " + ar.cause().getMessage());
//        }
//      });

//    client
//      .preparedQuery("SELECT id, name FROM student")
//      .execute(ar -> {
//        if (ar.succeeded()) {
//          RowSet<Row> rows = ar.result();
//          for (Row row : rows) {
//            System.out.println("User " + row.getString(0) + " " + row.getString(1));
//          }
//        } else {
//          System.out.println("Failure: " + ar.cause().getMessage());
//        }
//      });

//    client
//      .preparedQuery("INSERT INTO student (id, name) VALUES (?, ?)")
//      .execute(Tuple.of("3", "刘备"), ar -> {
//        if (ar.succeeded()) {
//          RowSet<Row> rows = ar.result();
//          System.out.println(rows.rowCount());
//        } else {
//          System.out.println("Failure: " + ar.cause().getMessage());
//        }
//      });

//    client
//      .preparedQuery("DELETE FROM student where id = ?")
//      .execute(Tuple.of("3"), ar -> {
//        if (ar.succeeded()) {
//          RowSet<Row> rows = ar.result();
//          System.out.println(rows.rowCount());
//        } else {
//          System.out.println("Failure: " + ar.cause().getMessage());
//        }
//      });

//    client
//      .preparedQuery("UPDATE student SET name = ? where id = ?")
//      .execute(Tuple.of("刘备update","3"), ar -> {
//        if (ar.succeeded()) {
//          RowSet<Row> rows = ar.result();
//          System.out.println(rows.rowCount());
//        } else {
//          System.out.println("Failure: " + ar.cause().getMessage());
//        }
//      });

//    client.withConnection(connection ->
//      connection
//        .preparedQuery("INSERT INTO student (id,name) VALUES (?, ?)")
//        .executeBatch(Arrays.asList(
//          Tuple.of("3", "刘备"),
//          Tuple.of("4", "关羽")
//        ))
//        .compose(res -> connection
//          // Do something with rows
//          .query("SELECT COUNT(*) FROM student")
//          .execute()
//          .map(rows -> rows.iterator().next().getInteger(0)))
//    ).onSuccess(count -> {
//      System.out.println("Insert student now the number of student is " + count);
//    });

//    client.getConnection()
//      // 事务必须使用连接
//      .onSuccess(conn -> {
//        // 事务开始
//        conn.begin()
//          .compose(tx -> conn
//            .query("INSERT INTO student (id, name) VALUES ('5','曹操')")
//            .execute()
//            .compose(res2 -> conn
//              .query("INSERT INTO student (id, name) VALUES ('6','司马懿')")
//              .execute())
//            // 提交事务
//            .compose(res3 -> tx.commit()))
//          // 将连接返回到连接池里面
//          .eventually(v -> conn.close())
//          .onSuccess(v -> System.out.println("Transaction succeeded"))
//          .onFailure(err -> System.out.println("Transaction failed: " + err.getMessage()));
//      });

//    pool.withTransaction(client -> client
//      .query("INSERT INTO student (id, name) VALUES ('5','曹操')")
//      .execute()
//      .flatMap(res -> client
//        .query("INSERT INTO student (id, name) VALUES ('6','司马懿')")
//        .execute()
//        // Map to a message result
//        .map("student inserted")))
//      .onSuccess(v -> System.out.println("Transaction succeeded"))
//      .onFailure(err -> System.out.println("Transaction failed: " + err.getMessage())
//      );

//    pool.preparedQuery("SELECT * FROM student WHERE age > 1", ar1 -> {
//      if (ar1.succeeded()) {
//        PreparedStatement pq = ar1.result();
//        // Create a cursor
//        Cursor cursor = pq.cursor(Tuple.of(18));
//        // Read 50 rows
//        cursor.read(50, ar2 -> {
//          if (ar2.succeeded()) {
//            RowSet<Row> rows = ar2.result();
//            // Check for more ?
//            if (cursor.hasMore()) {
//              // Repeat the process...
//            } else {
//              // No more rows - close the cursor
//              cursor.close();
//            }
//          }
//        });
//      }
//    });

//    Map<String, Object> parameters = Collections.singletonMap("id", 1);
//    SqlTemplate
//      .forQuery(pool, "SELECT * FROM student WHERE id=#{id}")
//      .execute(parameters)
//      .onSuccess(users -> {
//        users.forEach(row -> {
//          System.out.println(row.getString("id") + " " + row.getString("name"));
//        });
//      });

//    Map<String, Object> parameters = new HashMap<>();
//    parameters.put("id", "7");
//    parameters.put("name", "曹植");
//    SqlTemplate
//      .forUpdate(pool, "UPDATE student set name=#{name} where id= #{id}")
//      .execute(parameters)
//      .onSuccess(v -> {
//        System.out.println("Successful update");
//      });

//    RowMapper<Student> ROW_STUDENT_MAPPER = row -> {
//      Student student = new Student();
//      student.setId(row.getString("id"));
//      student.setName(row.getString("name"));
//      return student;
//    };
//
//    Map<String, Object> parameters = Collections.singletonMap("id", 1);
//    SqlTemplate
//      .forQuery(pool, "SELECT * FROM student WHERE id=#{id}")
//      .mapTo(ROW_STUDENT_MAPPER)
//      .execute(parameters)
//      .onSuccess(users -> {
//        users.forEach(student -> {
//          System.out.println(student.getId() + " " + student.getName());
//        });
//      });

//    SqlTemplate
//      .forQuery(pool, "SELECT * FROM student WHERE id=#{id}")
//      .mapTo(Row::toJson)
//      .execute(Collections.singletonMap("id", 1))
//      .onSuccess(users -> {
//        users.forEach(user -> {
//          System.out.println(user.encode());
//        });
//      });

//    TupleMapper<Student> PARAMETERS_STUDENT_MAPPER = TupleMapper.mapper(user -> {
//      Map<String, Object> parameters = new HashMap<>();
//      parameters.put("id", user.getId());
//      parameters.put("name", user.getName());
//      return parameters;
//    });
//
//    Student student = new Student();
//    student.setId("10");
//    student.setName("周瑜");
//    SqlTemplate
//      .forUpdate(pool, "INSERT INTO student (id,name) VALUES (#{id},#{name})")
//      .mapFrom(PARAMETERS_STUDENT_MAPPER)
//      .execute(student)
//      .onSuccess(res -> {
//        System.out.println("Student inserted");
//      });

//    JsonObject stu = new JsonObject();
//    stu.put("id", "11");
//    stu.put("name", "赵子龙");
//
//    SqlTemplate
//      .forUpdate(pool, "INSERT INTO student (id, name) VALUES (#{id},#{name})")
//      .mapFrom(TupleMapper.jsonObject())
//      .execute(stu)
//      .onSuccess(res -> {
//        System.out.println("Student inserted");
//      });


//    SqlTemplate
//      .forQuery(pool, "SELECT * FROM student WHERE id=#{id}")
//      .mapTo(Student.class)
//      .execute(Collections.singletonMap("id", 1))
//      .onSuccess(students -> {
//        students.forEach(student -> {
//          System.out.println(student.getId() + " " + student.getName());
//        });
//      })
//    .onFailure(event -> {
//      System.out.println(event.getMessage());
//    });

//    Student student = new Student();
//    student.setId("1");

    SqlTemplate
      .forQuery(pool, "SELECT * FROM student WHERE id=#{id}")
      .mapTo(Student.class)
      .execute(Collections.singletonMap("id", 1))
      .onSuccess(students -> {
        students.forEach(stu -> {
          System.out.println(stu.getId() + " " + stu.getName());
        });
      });


  }
}
