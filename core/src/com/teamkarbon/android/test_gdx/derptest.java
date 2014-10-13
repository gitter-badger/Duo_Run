package com.teamkarbon.android.test_gdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class derptest extends ApplicationAdapter {
    ShapeRenderer shapeRenderer;
    SpriteBatch batch;
    Box2DDebugRenderer debugRenderer;
    OrthographicCamera camera;
    World world;
    Body body;
    Body theFloor;
    Ball ball;
    BitmapFont font;

    Fixture ballFixture;
	
	@Override
	public void create () {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0f);
        camera.update();

        //Create world

        world = new World(new Vector2(0, -9.8f), true);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        //Create text
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(com.badlogic.gdx.graphics.Color.LIGHT_GRAY);
        font.setScale(4);

        //Init ball class
        ball = new Ball(60, 30);

        //Define the type of body for the ball
        BodyDef tempBD = new BodyDef();
        tempBD.type = BodyDef.BodyType.DynamicBody;
        tempBD.position.set(60, 30);

        //Create the actually body from body type
        body = world.createBody(tempBD);

        //Create a new rigidbody collider equivalent of Unity3D
        CircleShape pshape = new CircleShape();
        pshape.setRadius(30);

        //Define the physical properties of the body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = pshape;
        fixtureDef.density = 1f;
        fixtureDef.restitution = 0.45f;
        fixtureDef.friction = 0.3f;
        ballFixture = body.createFixture(fixtureDef);

        //Make the floor exist
        tempBD = new BodyDef();
        tempBD.type = BodyDef.BodyType.StaticBody;
        tempBD.position.set(0, 10);
        theFloor = world.createBody(tempBD);

        //Floor bounds
        PolygonShape floor = new PolygonShape();
        floor.setAsBox(camera.viewportWidth * 2, 10f);
        theFloor.createFixture(floor, 0f);

        debugRenderer = new Box2DDebugRenderer();

        //Save some memory
        pshape.dispose();
	}

	@Override
	public void render () {

        ball.setPos(body.getPosition().x, body.getPosition().y);

		Gdx.gl.glClearColor(0, 0.06f, 0.13f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        debugRenderer.render(world, camera.combined);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 100, 100, 100);
        shapeRenderer.circle(58, 32, 30);
        shapeRenderer.end();

        batch.begin();
        font.draw(batch, "coord: " + ball.x + ", " + ball.y, 300, 200);
        batch.end();

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
	}

    @Override
    public void dispose()
    {
        world.dispose();
        font.dispose();
    }

}
