package woojin.project.android.aop_part3_chapter04

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.bumptech.glide.Glide
import woojin.project.android.aop_part3_chapter04.databinding.ActivityDetailBinding
import woojin.project.android.aop_part3_chapter04.model.Book
import woojin.project.android.aop_part3_chapter04.model.Review

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = getAppDatabase(this)

        val bookModel = intent.getParcelableExtra<Book>("bookModel")

        binding.titleTextView.text = bookModel?.title.orEmpty()
        binding.descriptionTextView.text = bookModel?.description.orEmpty()

        Glide
            .with(binding.coverImageView.context)
            .load(bookModel?.coverSmallUrl)
            .into(binding.coverImageView)

        Thread {
            val review = db.reviewDao().getOneReview(bookModel?.id?.toInt() ?: 0)
            runOnUiThread {
                binding.reviewEditText.setText(review?.review.orEmpty())
            }
        }.start()

        binding.saveButton.setOnClickListener {
            Thread {
                db.reviewDao().saveReview(
                    Review(bookModel?.id?.toInt() ?: 0, binding.reviewEditText.text.toString())
                )
            }.start()
        }
    }
}