package opencodes.nextmatch24

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class PointView: View{

    var whitePaint: Paint = Paint()
    var redPaint: Paint = Paint()
    var r: Float = 60F
    var fragment: QuizFragment

    var x0: Float = 540F
    var y0: Float = 503F

    constructor(context: Context, fragment: QuizFragment): super(context) {
        Log.e("cycle","constructor")
        this.fragment = fragment
        init()
        setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                Log.e("clicked", event.actionMasked.toString())
                x0 = event.x
                y0 = event.y

                invalidate()



                if(event.actionMasked == MotionEvent.ACTION_UP)
                    released()

                if(event.actionMasked == MotionEvent.ACTION_CANCEL){
                    x0 = 540F
                    y0 = 503F
                }

                return true
            }
        })
    }


    fun init() {
        redPaint.color = ContextCompat.getColor(context, R.color.colorPrimary)
        redPaint.setStyle(Paint.Style.FILL)
        whitePaint.setARGB (255, 255, 255, 255)
        whitePaint.setStyle(Paint.Style.FILL)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        Log.e("cycle","onDraw")

        canvas.drawCircle(x0, y0, 70F, redPaint)
        canvas.drawCircle(x0, y0,r, whitePaint)
    }

    fun released(){
        fragment.nextQuestion()
        x0 = 540F
        y0 = 503F
    }

}