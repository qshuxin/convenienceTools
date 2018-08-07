# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class CartoonItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    dir_name = scrapy.Field()
    link_url = scrapy.Field()
    img_url = scrapy.Field()
    image_paths = scrapy.Field()
    #pass
