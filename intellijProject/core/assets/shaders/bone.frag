varying vec4 v_color;
varying vec2 v_texCoord0;

uniform float x;
uniform float y;
uniform float x2;
uniform float y2;
uniform float alfa;
uniform float alfa2;
uniform sampler2D u_sampler2D;

vec2 turn(float alfa, float x, float y, float x2, float y2);
float getRadius(vec2 p1, vec2 p2);
float getSquare(vec2 p1, vec2 p2);
float checkBetween(vec2 a, vec2 b, vec2 c);
float sigmoid(float x);

float sigmoid(float x) {
    return 1.0/(1+pow(2.71828182846, -x));
}

vec2 turn(float alfa, float x, float y, float x2, float y2) {
    float dx = x - x2;
    float dy = y - y2;
    float r = sqrt(dx*dx + dy*dy);
    float alfa2 = atan2(dy, dx);
    float xx = x + cos(alfa + alfa2 + 3.14)*r;
    float yy = y + sin(alfa + alfa2 + 3.14)*r;
    return vec2(xx, yy);
}

float getRadius(vec2 p1, vec2 p2) {
    return sqrt((p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y));
}

float getSquare(vec2 p1, vec2 p2) {
    return (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y);
}

float checkBetween(vec2 a, vec2 b, vec2 c) {
    float ab = getSquare(a, b);
    float ac = getSquare(a, c);
    float cb = getSquare(c, b);
    return min((ab+ac)/cb, (ab+cb)/ac);
}

void main() {
    vec2 p1 = turn(alfa, x, y, v_texCoord0.x, v_texCoord0.y);
    vec2 p2 = p1;
    vec2 p3 = turn(-alfa2-alfa, x2, y2, x, y);
    float c2 = getRadius(p3, vec2(x2, y2)) - getRadius(p3, p1);
    float c = getRadius(vec2(x, y), vec2(x2, y2)) - getRadius(vec2(x, y), p1);
    c = sigmoid((c+c2)*100);

    p2 = turn((alfa + alfa2)*(1-c), x2, y2, p1.x, p1.y);
    vec4 color = texture2D(u_sampler2D, p2) * v_color;
	gl_FragColor = color;
}
